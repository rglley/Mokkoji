package online.mokkoji.result.service;

import online.mokkoji.result.domain.Message;
import online.mokkoji.result.domain.Photo;

public interface ResultService {

    void createPhoto(Photo photo);

    void createMessage(Message message);

    void saveRemainingPhotos();

    void saveRemainingMessages();
}
