package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    DUPLICATE_SIGNUP(409, "이미 존재하는 회원입니다."),
    USER_NOT_FOUND(404,"존재하지 않는 회원입니다.");

    private final Integer errorCode;
    private final String errorMessage;
}

