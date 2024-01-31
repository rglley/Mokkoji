package online.mokkoji.config;

import lombok.AllArgsConstructor;
import online.mokkoji.result.domain.Photo;
import online.mokkoji.result.repository.PhotoRepository;
import org.redisson.api.MapCacheOptions;
import org.redisson.api.MapOptions;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.MapWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class CacheConfig {

    private final RedissonClient redissonClient;
    private final PhotoRepository photoRepository;

    @Bean
    public RMapCache<String, Photo> photoRMapCache() {
        final RMapCache<String, Photo> photoRMapCache
                = redissonClient.getMapCache("photos", MapCacheOptions.<String, Photo>defaults()
                .writer(getPhotoMapWriter())
                .writeMode(MapOptions.WriteMode.WRITE_BEHIND)
                .writeBehindBatchSize(5000)
                // TODO : 2024.01.31 실제로는 시간 분단위로 하기
                .writeBehindDelay(30000));

        return photoRMapCache;
    }

    private MapWriter<String, Photo> getPhotoMapWriter() {
        return new MapWriter<String, Photo>() {
            @Override
            public void write(Map<String, Photo> map) {
                map.forEach((k, v) -> {
                    photoRepository.save(v);
                });
            }

            @Override
            public void delete(Collection<String> keys) {
                // TODO : 2024.01.31 url로 삭제 시 삭제되게 하고싶은데 이상함
                keys.stream().forEach(key -> {
                    photoRepository.deleteByUrl(key);
                });

            }
        };
    }

}
