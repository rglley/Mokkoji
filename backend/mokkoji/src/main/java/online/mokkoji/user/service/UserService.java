package online.mokkoji.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.user.domain.*;
import online.mokkoji.user.domain.Record;
import online.mokkoji.user.dto.request.SignupDto;
import online.mokkoji.user.dto.request.UpdateDto;
import online.mokkoji.user.dto.response.MyPageDto;
import online.mokkoji.user.dto.response.UpdatePageDto;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.UserErrorCode;
import online.mokkoji.user.domain.UserAccount;
import online.mokkoji.user.repository.AccountRepository;
import online.mokkoji.user.repository.RecordRepository;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    //create, read, update, delete
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RecordRepository recordRepository;
    private final JwtUtil jwtService;

    public UpdatePageDto readUpdatePage(String provider, String email) {
        log.info("회원 정보 조회(provider, email) : {}, {}", provider, email);
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            log.error("존재하지 않는 회원 정보. 조회 불가능");
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        log.info("회원 정보 반환");
        User readUser = findUser.get();
        UserAccount userAccount = readUser.getUserAccount();

        if (userAccount == null) {
            log.info("계좌 연동 X");
            UpdatePageDto updatePageDto = new UpdatePageDto(readUser.getEmail(), readUser.getImage(), readUser.getName(),
                    null, null);

            return updatePageDto;
        }

        log.info("계좌 연동 O");
        UpdatePageDto updatePageDto = new UpdatePageDto(readUser.getEmail(), readUser.getImage(), readUser.getName(),
                userAccount.getBank(), userAccount.getNumber());

        return updatePageDto;
    }

    public MyPageDto readMypage(String provider, String email) {
        log.info("회원 정보 조회(provider, email) : {}, {}", provider, email);
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            log.error("존재하지 않는 회원 정보. 조회 불가능");
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        log.info("회원 정보 반환");
        User readUser = findUser.get();
        UserAccount userAccount = readUser.getUserAccount();
        Record record = readUser.getRecord();

        if (userAccount == null) {
            log.info("계좌 연동 X");
            MyPageDto myPageDto = new MyPageDto(readUser.getEmail(), readUser.getName(), false,
                    record.getEventCount(), record.getTotalTime(), record.getTotalParticipant(), record.getTotalMessage());

            return myPageDto;
        }

        log.info("계좌 연동 O");
        MyPageDto myPageDto = new MyPageDto(readUser.getEmail(), readUser.getName(), true,
                record.getEventCount(), record.getTotalTime(), record.getTotalParticipant(), record.getTotalMessage());

        return myPageDto;
    }

    public void createUser(String provider, String email, SignupDto signupDto) {
        log.info("회원 중복 체크(provider, email) : {}, {}", provider, email);
        Optional<User> findUser = userRepository.findByProviderAndEmail
                (Provider.valueOf(provider), email);

        if(findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        if (findUser.get().getAuthority().getKey().equals("ROLE_USER")) {
            log.error("이미 존재하는 회원. 회원가입 불가능");
            throw new RestApiException(UserErrorCode.DUPLICATE_SIGNUP);
        }

        log.info("회원가입 진행");
        // TODO: 2024.01.29 이름 null이면 랜덤 닉네임 부여?!?!?
        String refreshToken = jwtService.createRefreshToken();
        User newUser = new User(provider, email, signupDto.getName(), signupDto.getImage(), Authority.USER, refreshToken);

        userRepository.save(newUser);

        String bank = signupDto.getBank();
        String accountNumber = signupDto.getAccountNumber();

        if (bank != null && accountNumber != null) {
            log.info("계좌 정보 확인, 등록 진행");
            UserAccount userAccount = newUser.getUserAccount();
            userAccount.toEntity(newUser, bank, accountNumber);

            accountRepository.save(userAccount);
        }
    }

    public void updateUser(String provider, String email, UpdateDto modifyDto) {
        log.info("회원 정보 조회(provider, email) : {}, {}", provider, email);
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            log.error("존재하지 않는 회원 정보. 수정 불가능");
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        log.info("회원 정보 수정 진행");
        String name = modifyDto.getName();
        String image = modifyDto.getImage();
        String bank = modifyDto.getBank();
        String accountNumber = modifyDto.getAccountNumber();

        User updateUser = findUser.get();
        updateUser.updateUser(provider, email, name, image, Authority.USER);

        userRepository.save(updateUser);

        if (bank != null && accountNumber != null) {
            log.info("계좌 정보 업데이트");
            UserAccount userAccount = new UserAccount();
            userAccount.toEntity(findUser.get(), bank, accountNumber);

            accountRepository.save(userAccount);
        }
    }

    public User deleteUser(String provider, String email) {
        log.info("회원 조회(provider, email) : {}, {}", provider, email);
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            log.error("존재하지 않는 회원 정보. 탈퇴 불가능");
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        log.info("회원 탈퇴 진행");
        User deleteUser = findUser.get();
        userRepository.delete(deleteUser);

        return deleteUser;
    }
}
