package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OpenviduErrorCode implements ErrorCode {

    NO_USER_ID(400, "유저 아이디 없거나 일치하는 아이디 없음"),
    NOT_HOST_USER_ID(403, "호스트와 동일한 아이디가 아님"),
    ;

    private final Integer errorCode;
    private final String errorMessage;
}
