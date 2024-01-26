package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EventErrorCode implements ErrorCode {

    ALREADY_CLOSED_EVENT(HttpStatus.CONFLICT, "이미 닫힌 세션"), //409
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
