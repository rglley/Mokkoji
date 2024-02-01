package online.mokkoji.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.user.domain.*;
import online.mokkoji.user.domain.Record;
import online.mokkoji.user.dto.request.UserInputReqDto;
import online.mokkoji.user.dto.response.MyPageResDto;
import online.mokkoji.user.dto.response.UpdatePageResDto;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.UserErrorCode;
import online.mokkoji.user.domain.UserAccount;
import online.mokkoji.user.repository.AccountRepository;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final JwtUtil jwtService;

    public UpdatePageResDto readUpdatePage(String provider, String email) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        User readUser = findUser.get();
        UserAccount userAccount = readUser.getUserAccount();

        if (userAccount == null) {
            return new UpdatePageResDto(readUser.getEmail(), readUser.getImage(), readUser.getName(),
                    null, null);
        }

        return new UpdatePageResDto(readUser.getEmail(), readUser.getImage(), readUser.getName(),
                userAccount.getBank(), userAccount.getNumber());
    }

    public MyPageResDto readMypage(String provider, String email) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        User readUser = findUser.get();
        UserAccount userAccount = readUser.getUserAccount();
        Record record = readUser.getRecord();

        if (userAccount == null) {
            return new MyPageResDto(readUser.getEmail(), readUser.getName(), false,
                    record.getEventCount(), record.getTotalTime(), record.getTotalParticipant(), record.getTotalMessage());
        }

        return new MyPageResDto(readUser.getEmail(), readUser.getName(), true,
                record.getEventCount(), record.getTotalTime(), record.getTotalParticipant(), record.getTotalMessage());
    }

    public void createUser(String provider, String email, UserInputReqDto userInputReqDto) {
        Optional<User> findUser = userRepository.findByProviderAndEmail
                (Provider.valueOf(provider), email);

        if(findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        if (findUser.get().getAuthority().getKey().equals("ROLE_USER")) {
            throw new RestApiException(UserErrorCode.DUPLICATE_SIGNUP);
        }

        String refreshToken = jwtService.createRefreshToken();
        User newUser = new User(provider, email, userInputReqDto.getName(), userInputReqDto.getImage(), Authority.USER, refreshToken);

        userRepository.save(newUser);

        String bank = userInputReqDto.getBank();
        String accountNumber = userInputReqDto.getAccountNumber();

        if (bank != null && accountNumber != null) {
            UserAccount userAccount = newUser.getUserAccount();
            userAccount.toEntity(newUser, bank, accountNumber);

            accountRepository.save(userAccount);
        }
    }

    public void updateUser(String provider, String email, UserInputReqDto modifyDto) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        String name = modifyDto.getName();
        String image = modifyDto.getImage();
        String bank = modifyDto.getBank();
        String accountNumber = modifyDto.getAccountNumber();

        User updateUser = findUser.get();
        updateUser.updateUser(provider, email, name, image, Authority.USER);

        userRepository.save(updateUser);

        if (bank != null && accountNumber != null) {
            UserAccount userAccount = new UserAccount();
            userAccount.toEntity(findUser.get(), bank, accountNumber);

            accountRepository.save(userAccount);
        }
    }

    public User deleteUser(String provider, String email) {
        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);

        if (findUser.isEmpty()) {
            throw new RestApiException(UserErrorCode.USER_NOT_FOUND);
        }

        User deleteUser = findUser.get();
        userRepository.delete(deleteUser);

        return deleteUser;
    }
}
