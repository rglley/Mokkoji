package online.mokkoji.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorcode.*;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.dto.request.UserInputReqDto;
import online.mokkoji.user.dto.response.MyPageResDto;
import online.mokkoji.user.dto.response.UpdatePageResDto;
import online.mokkoji.user.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.version}/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final JwtUtil jwtService;

    /**
     *
     * @param req 유저 Access Token
     * @return 업데이트 된 유저 정보
     */
    @GetMapping("/userinfo")
    public ResponseEntity<UpdatePageResDto> getUserInfo(HttpServletRequest req) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        UpdatePageResDto updatePageResDto = userServiceImpl.getUpdatePage(provider, email);

        return new ResponseEntity<>(updatePageResDto, HttpStatus.OK);
    }

    /**
     *
     * @param req 유저 Access Token
     * @return 유저 정보
     */
    @GetMapping("/mypage")
    public ResponseEntity<MyPageResDto> getMyPage(HttpServletRequest req) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        MyPageResDto myPageResDto = userServiceImpl.getMypage(provider, email);

        return new ResponseEntity<>(myPageResDto, HttpStatus.OK);
    }

    /**
     *
     * @param req 유저 Access Token
     * @param userInputReqDto 회원가입 정보
     * @return 회원가입된 유저 정보
     */
    @PostMapping
    public ResponseEntity<UserInputReqDto> addUser(HttpServletRequest req,
                                                   @Valid @RequestBody UserInputReqDto userInputReqDto) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        log.info("회원가입 요청");
        userServiceImpl.createUser(provider, email, userInputReqDto);

        return new ResponseEntity<>(userInputReqDto, HttpStatus.CREATED);
    }

    /**
     *
     * @param req 유저 Access Token
     * @param modifyDto 유저 수정정보
     * @return 수정된 유저
     */
    @PutMapping
    public ResponseEntity<UserInputReqDto> editUser(HttpServletRequest req,
                                                    @Valid @RequestBody UserInputReqDto modifyDto) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        userServiceImpl.updateUser(provider, email, modifyDto);

        return new ResponseEntity<>(modifyDto, HttpStatus.OK);
    }

    /**
     *
     * @param req 유저 Access Token
     * @return 200 코드
     */
    @DeleteMapping
    public ResponseEntity<User> removeUser(HttpServletRequest req) {
        String provider = jwtService.getProvider(req);
        String email = jwtService.getEmail(req);

        userServiceImpl.deleteUser(provider, email);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
