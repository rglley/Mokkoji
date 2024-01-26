package online.mokkoji.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.api.request.ModifyDto;
import online.mokkoji.api.request.SignupDto;
import online.mokkoji.api.service.UserService;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.CommonErrorCode;
import online.mokkoji.db.entity.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    //add, get, edit, remove
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody SignupDto signupDto) {
        log.info("회원 가입 요청, 요청 정보 : {}", signupDto.toString());
        User newUser = userService.signup(signupDto);
        log.info("회원 가입 완료, 등록 정보 : {}", newUser.toString());
        //프론트로도 맵에 담아서 쏴주자
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editUser(@Valid @RequestBody ModifyDto modifyDto) {


        log.info("회원 정보 수정 요청, 요청 정보 : {}", modifyDto.toString());
//        User editUser = userService.updateUser(modifyDto);
//        log.info("회원 정보 수정 완료, 수정 정보 : {}", editUser.toString());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
