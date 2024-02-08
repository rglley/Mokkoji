package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultErrorCode implements ErrorCode {

    RESULT_NOT_FOUND(404, "존재하지 않는 결과물입니다"),
    BACKGROUND_NOT_FOUND(404, "존재하지 않는 배경 템플릿입니다"),
    POSTIT_NOT_FOUND(404, "존재하지 않는 포스트잇 템플릿입니다");

    private final Integer errorCode;
    private final String errorMessage;
}

