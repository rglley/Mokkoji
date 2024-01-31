package online.mokkoji.result.service;

import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.result.domain.Result;
import online.mokkoji.user.domain.Provider;

import java.util.List;
import java.util.Map;

public interface ResultService {
    //create, read, update, delete
    Map<String, Object> readResult(String provider, String email);

}
