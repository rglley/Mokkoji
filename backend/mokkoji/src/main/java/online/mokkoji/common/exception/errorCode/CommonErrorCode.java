package online.mokkoji.common.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

//전역 에러코드 정의
@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    // 3xx(리다이렉션) : 추가 작업 조치


    // 4xx(클라이언트 오류) : 요청 문법 오류, 요청 처리 실패
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 매개변수가 포함되었습니다."), // 400
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청 내용을 찾을 수 없습니다."), // 404


    // 5xx(서버 오류) : 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."); // 500


    private final HttpStatus httpStatus;
    private final String message;
}
