package online.mokkoji.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.api.request.SignupDto;
import online.mokkoji.common.auth.jwt.JwtService;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.UserErrorCode;
import online.mokkoji.db.entity.Provider;
import online.mokkoji.db.entity.User;
import online.mokkoji.db.repository.AccountRepository;
import online.mokkoji.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    //create, read, update, delete
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public User signup(SignupDto signupDto) {
        //provider, email로 회원 중복 체크
        log.info("회원 중복 체크(provider, email) : {}, {}", signupDto.getProvider(), signupDto.getEmail());
        Optional<User> findUser = userRepository.findByProviderAndEmail
                (Provider.valueOf(signupDto.getProvider()), signupDto.getEmail());

        //회원 중복
        if(!findUser.isEmpty()) {
            log.info("이미 존재하는 회원. 회원가입 불가능");
            throw new RestApiException(UserErrorCode.DUPLICATE_SIGNUP);
        } else {
            log.info("회원가입 진행");
            User user = signupDto.toEntitiy();

            User newUser = userRepository.save(user);
            log.info("회원가입 성공, SignupInfo : {}", newUser.toString());
            return newUser;
        }
    }
}
