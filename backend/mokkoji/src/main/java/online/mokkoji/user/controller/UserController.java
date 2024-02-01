package online.mokkoji.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.user.dto.request.SignupDto;
import online.mokkoji.user.dto.request.UpdateDto;
import online.mokkoji.user.dto.response.MyPageDto;
import online.mokkoji.user.dto.response.UpdatePageDto;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    //add, get, edit, remove
    private final UserService userService;
    private final JwtUtil jwtService;

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest req) {
        log.info("회원 정보 수정으로 이동 요청");
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        UpdatePageDto updatePageDto = userService.readUpdatePage(provider, email);
        log.info("회원 정보 수정으로 이동 성공");

        return new ResponseEntity<>(updatePageDto, HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<MyPageDto> getMyPage(HttpServletRequest req) {
        log.info("마이페이지로 이동 요청");
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        MyPageDto myPageDto = userService.readMypage(provider, email);
        log.info("마이페이지로 이동 성공");

        return new ResponseEntity<>(myPageDto, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<SignupDto> addUser(HttpServletRequest req, @Valid @RequestBody SignupDto signupDto) {
        log.info("회원 가입 요청, signupDto : {}", signupDto.toString());
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        userService.createUser(provider, email, signupDto);
        log.info("회원 가입 성공");

        return new ResponseEntity<>(signupDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UpdateDto> editUser(HttpServletRequest req, @Valid @RequestBody UpdateDto modifyDto) {
        log.info("회원 정보 수정 요청, modifyDto : {}", modifyDto.toString());
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        userService.updateUser(provider, email, modifyDto);
        log.info("회원 정보 수정 완료");

        return new ResponseEntity<>(modifyDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<User> removeUser(HttpServletRequest req) {
        log.info("회원 탈퇴 요정");
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        User removeUser = userService.deleteUser(provider, email);
        log.info("회원 탈퇴 완료");

        return new ResponseEntity<>(removeUser, HttpStatus.OK);
    }
}
