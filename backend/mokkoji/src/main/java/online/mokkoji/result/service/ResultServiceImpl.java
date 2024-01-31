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
    private final RMapCache<Long, Photo> photoRMapCache;

    @Override
    public Photo createPhoto(Photo photo) {
        Photo savedPhoto = photoRMapCache.put(1L, photo);
        return savedPhoto;
    }
}
