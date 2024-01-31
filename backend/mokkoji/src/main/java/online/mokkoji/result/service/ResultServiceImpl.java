package online.mokkoji.result.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import online.mokkoji.result.domain.Message;
import online.mokkoji.result.domain.Photo;
import online.mokkoji.result.repository.MessageRepository;
import online.mokkoji.result.repository.PhotoRepository;
import org.redisson.api.RMapCache;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {

    //    private final RedisTemplate<String, Object> redisTemplate;
    private final RMapCache<String, Photo> photoRMapCache;
    private final RMapCache<String, Message> messageRMapCache;
    private final PhotoRepository photoRepository;
    private final MessageRepository messageRepository;

    @Override
    public void createPhoto(Photo photo) {
        photoRMapCache.put(photo.getUrl(), photo);
    }

    @Override
    public void createMessage(Message message) {
        messageRMapCache.put(message.getWriter(), message);
    }

    @Override
    public void saveRemainingPhotos() {
        for (Map.Entry<String, Photo> entry : photoRMapCache.entrySet()) {
            photoRepository.save(entry.getValue());
            photoRMapCache.remove(entry);
        }

    }

    @Override
    public void saveRemainingMessages() {
        for (Map.Entry<String, Message> entry : messageRMapCache.entrySet()) {
            messageRepository.save(entry.getValue());
            photoRMapCache.remove(entry);
            
        }
    }
}
