package online.mokkoji.user.service;

import online.mokkoji.user.domain.User;
import online.mokkoji.user.dto.request.SignupDto;
import online.mokkoji.user.dto.request.UpdateDto;
import online.mokkoji.user.dto.response.MyPageDto;
import online.mokkoji.user.dto.response.UpdatePageDto;

public interface UserService {
    UpdatePageDto readUpdatePage(String provider, String email);

    MyPageDto readMypage(String provider, String email);

    void createUser(String provider, String email, SignupDto signupDto);

    void updateUser(String provider, String email, UpdateDto modifyDto);

    User deleteUser(String provider, String email);

    User getByProviderAndEmail(String provider, String email);
}
