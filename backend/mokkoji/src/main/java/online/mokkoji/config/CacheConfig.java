package online.mokkoji.config;

import lombok.AllArgsConstructor;
import online.mokkoji.result.domain.Message;
import online.mokkoji.result.domain.Photo;
import online.mokkoji.result.repository.MessageRepository;
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
    private final MessageRepository messageRepository;


    @Bean
    public RMapCache<String, Photo> photoRMapCache() {
        final RMapCache<String, Photo> photoRMapCache
                = redissonClient.getMapCache("photos", MapCacheOptions.<String, Photo>defaults()
                .writer(getPhotoMapWriter())
                .writeMode(MapOptions.WriteMode.WRITE_BEHIND)
                .writeBehindBatchSize(5000)
                // TODO : 2024.01.31 실제로는 시간 분단위로 하기
                .writeBehindDelay(60000));

        return photoRMapCache;
    }

    private MapWriter<String, Photo> getPhotoMapWriter() {
        return new MapWriter<String, Photo>() {
            @Override
            public void write(Map<String, Photo> map) {
                map.forEach((k, v) -> {
                    photoRepository.save(v);
                    redissonClient.getMapCache("photos").remove(k);
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

    @Bean
    public RMapCache<String, Message> messageRMapCache() {
        final RMapCache<String, Message> messageRMapCache
                = redissonClient.getMapCache("messages", MapCacheOptions.<String, Message>defaults()
                .writer(getMessageMapWriter())
                .writeMode(MapOptions.WriteMode.WRITE_BEHIND)
                .writeBehindBatchSize(5000)
                // TODO : 2024.01.31 실제로는 시간 분단위로 하기
                .writeBehindDelay(2000));

        return messageRMapCache;
    }

    private MapWriter<String, Message> getMessageMapWriter() {
        return new MapWriter<String, Message>() {
            @Override
            public void write(Map<String, Message> map) {
                map.forEach((k, v) -> {
                    messageRepository.save(v);
                    redissonClient.getMapCache("messages").remove(k);
                });
            }

            @Override
            public void delete(Collection<String> keys) {
                // TODO : 2024.01.31 url로 삭제 시 삭제되게 하고싶은데 이상함
//                keys.stream().forEach(key -> {
//                    messageRepository.deleteByUrl(key);
//                });

            }
        };
    }

}
