package online.mokkoji.user.service;

import online.mokkoji.user.domain.User;
import online.mokkoji.user.dto.request.UserInputReqDto;
import online.mokkoji.user.dto.response.MyPageResDto;
import online.mokkoji.user.dto.response.UpdatePageResDto;

public interface UserService {
    UpdatePageResDto getUpdatePage(String provider, String email);

    MyPageResDto getMypage(String provider, String email);

    void createUser(String provider, String email, UserInputReqDto userInputReqDto);

    void updateUser(String provider, String email, UserInputReqDto modifyDto);

    void deleteUser(String provider, String email);
}
