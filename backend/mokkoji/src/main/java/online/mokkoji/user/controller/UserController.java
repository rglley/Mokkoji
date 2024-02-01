package online.mokkoji.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.user.dto.request.UserInputReqDto;
import online.mokkoji.user.dto.response.MyPageResDto;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.dto.response.UpdatePageResDto;
import online.mokkoji.user.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    //add, get, edit, remove
    private final UserServiceImpl userService;
    private final JwtUtil jwtService;

    @GetMapping("/update")
    public ResponseEntity<?> mvToUpdate(HttpServletRequest req) {
        log.info("회원 정보 수정으로 이동 요청");
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        UpdatePageResDto updatePageDto = userService.getUpdatePage(provider, email);
        log.info("회원 정보 수정으로 이동 성공");

        return new ResponseEntity<>(updatePageDto, HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<MyPageResDto> getMyPage(HttpServletRequest req) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        MyPageResDto myPageResDto = userService.getMypage(provider, email);

        return new ResponseEntity<>(myPageResDto, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<UserInputReqDto> addUser(HttpServletRequest req,
                                                   @Valid @RequestBody UserInputReqDto signupDto) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        userService.createUser(provider, email, signupDto);
        log.info("회원 가입 성공");

        return new ResponseEntity<>(signupDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserInputReqDto> editUser(HttpServletRequest req,
                                                    @Valid @RequestBody UserInputReqDto modifyDto) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        userService.updateUser(provider, email, modifyDto);
        log.info("회원 정보 수정 완료");

        return new ResponseEntity<>(modifyDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<User> removeUser(HttpServletRequest req) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        User removeUser = userService.deleteUser(provider, email);
        log.info("회원 탈퇴 완료");

        return new ResponseEntity<>(removeUser, HttpStatus.OK);
    }
}
