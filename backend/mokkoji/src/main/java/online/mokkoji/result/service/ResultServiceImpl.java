package online.mokkoji.result.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import online.mokkoji.result.domain.Photo;
import org.redisson.api.RMapCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RMapCache<String, Photo> photoRMapCache;

    @Override
    public void createPhoto(Photo photo) {
        photoRMapCache.put(photo.getUrl(), photo);
    }
}
