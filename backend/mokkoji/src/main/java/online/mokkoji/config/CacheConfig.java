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
    public RMapCache<Long, Photo> photoRMapCache() {
        final RMapCache<Long, Photo> photoRMapCache
                = redissonClient.getMapCache("photos", MapCacheOptions.<Long, Photo>defaults()
                .writer(getPhotoMapWriter())
                .writeMode(MapOptions.WriteMode.WRITE_BEHIND)
                .writeBehindBatchSize(5000)
                .writeBehindDelay(1000));

        return photoRMapCache;
    }

    private MapWriter<Long, Photo> getPhotoMapWriter() {
        return new MapWriter<Long, Photo>() {
            @Override
            public void write(Map<Long, Photo> map) {
                map.forEach((k, v) -> {
//                    Result result = resultRepository.findById(v.getResultId())
//                            .orElseThrow(() -> new RestApiException(ResultErrorCode.NO_RESULT_ID));
//                    ;
//                    Photo photo = new Photo(result, v.getPhotoUrl());
                    photoRepository.save(v);
                });
            }

            @Override
            public void delete(Collection<Long> keys) {
                // TODO : 2024.01.31 url로 삭제 시 삭제되게 하고싶은데 이상함
                keys.stream().forEach(key -> {
                    photoRepository.deleteById(key);
                });

            }
        };
    }

}
