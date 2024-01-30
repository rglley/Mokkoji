package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OpenviduErrorCode implements ErrorCode {

    NO_USER_ID(400, "userId가 없거나 일치하는 userId 없음"),
    NOT_HOST_USER_ID(403, "호스트와 동일한 userId가 아님"),
    ;

    private final Integer errorCode;
    private final String errorMessage;
}
