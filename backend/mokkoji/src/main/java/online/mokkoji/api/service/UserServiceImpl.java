package online.mokkoji.api.service;

import lombok.RequiredArgsConstructor;
import online.mokkoji.api.request.UserSignupDto;
import online.mokkoji.db.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void createUser(UserSignupDto userSignupDto) throws Exception {

    }
}
