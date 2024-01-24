package online.mokkoji.api.service;

import online.mokkoji.api.request.UserSignupDto;
import org.springframework.stereotype.Service;

public interface UserService {
    public void createUser(UserSignupDto userSignupDto) throws Exception;


}
