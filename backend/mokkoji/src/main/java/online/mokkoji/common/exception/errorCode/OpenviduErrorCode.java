package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OpenviduErrorCode implements ErrorCode {

    NO_USER_ID(HttpStatus.BAD_REQUEST, "유저 아이디 없거나 일치하는 아이디 없음"), //400
    NOT_HOST_USER_ID(HttpStatus.FORBIDDEN, "호스트와 동일한 아이디가 아님"), //403
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
