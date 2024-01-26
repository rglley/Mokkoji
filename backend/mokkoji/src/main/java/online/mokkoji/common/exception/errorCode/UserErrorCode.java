package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    DUPLICATE_SIGNUP(HttpStatus.CONFLICT, "이미 존재하는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

