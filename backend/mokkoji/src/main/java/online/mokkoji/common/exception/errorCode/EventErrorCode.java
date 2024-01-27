package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventErrorCode implements ErrorCode {

    ALREADY_CLOSED_EVENT(409, "이미 닫힌 세션"),
    ;


    private final Integer errorCode;
    private final String errorMessage;
}
