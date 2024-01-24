package online.mokkoji.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.api.request.UserSignupDto;
import online.mokkoji.api.service.UserService;
import online.mokkoji.api.service.UserServiceImpl;
import online.mokkoji.common.auth.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    //add, get, edit, remove
    private final UserServiceImpl userService;
    //로그인 ??

    //로그아웃 시 refreshToken 처리(삭제?)

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody UserSignupDto userSignupDto) {
        log.info("회원가입 요청, UserSignupDto - {}", userSignupDto);
        try {
            userService.createUser(userSignupDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
