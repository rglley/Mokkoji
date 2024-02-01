package online.mokkoji.result.service;

import online.mokkoji.result.domain.Message;
import online.mokkoji.result.domain.Photo;

import java.util.Map;

public interface ResultService {
    //create, read, update, delete
    Map<String, Object> readResult(String provider, String email);

    void createPhoto(Photo photo);

    void createMessage(Message message);

    void saveRemainingPhotos();

    void saveRemainingMessages();
}
