package online.mokkoji.api.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.api.request.ModifyDto;
import online.mokkoji.api.request.SignupDto;
import online.mokkoji.common.auth.jwt.JwtService;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.UserErrorCode;
import online.mokkoji.db.entity.Account;
import online.mokkoji.db.entity.Provider;
import online.mokkoji.db.entity.User;
import online.mokkoji.db.repository.AccountRepository;
import online.mokkoji.db.repository.JwtRepository;
import online.mokkoji.db.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    //create, read, update, delete
    private final JwtRepository jwtRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public User signup(SignupDto signupDto) {
        log.info("회원 중복 체크(provider, email) : {}, {}", signupDto.getProvider(), signupDto.getEmail());
        Optional<User> findUser = userRepository.findByProviderAndEmail
                (Provider.valueOf(signupDto.getProvider()), signupDto.getEmail());

        if(!findUser.isEmpty()) {
            log.info("이미 존재하는 회원. 회원가입 불가능");
            throw new RestApiException(UserErrorCode.DUPLICATE_SIGNUP);
        }

        log.info("회원가입 진행");
        User user = signupDto.toEntitiy();
        User newUser = userRepository.save(user);

        //계좌 정보 입력했으면 추가
        String bank = signupDto.getBank();
        String accountNumber = signupDto.getAccountNumber();
        if(bank != null && accountNumber != null) {
            Account newAccount = signupDto.toEntity(user);
            accountRepository.save(newAccount);
        }

        log.info("회원가입 성공, SignupInfo : {}", newUser.toString());
        return newUser;
    }

    public User editUser(@Valid @RequestBody ModifyDto modifyDto) {
        log.info("회원 정보 조회(provider, email) : {}, {}", modifyDto.getProvider(), modifyDto.getEmail());

        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(modifyDto.getProvider()), modifyDto.getEmail());

        if(findUser.isEmpty()) {
            log.info("존재하지 않는 회원 정보. 수정 불가능");
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

    }
}
