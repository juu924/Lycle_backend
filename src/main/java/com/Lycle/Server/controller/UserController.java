package com.Lycle.Server.controller;

import com.Lycle.Server.dto.BasicResponse;
import com.Lycle.Server.dto.User.UserJoinDto;
import com.Lycle.Server.dto.User.UserLoginDto;
import com.Lycle.Server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/join")
    public ResponseEntity<BasicResponse> joinUser(@RequestBody UserJoinDto userJoinDto) {
        userService.saveUser(userJoinDto);
        BasicResponse joinUserResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.CREATED)
                .message("회원가입이 완료되었습니다.")
                .build();
        return new ResponseEntity<>(joinUserResponse, joinUserResponse.getHttpStatus());

    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponse> loginUser(@RequestBody UserLoginDto userLoginDto) {

        BasicResponse searchUser = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("로그인에 성공했습니다.")
                .count(1)
                .token(userService.loginUser(userLoginDto))
                .build();
        return new ResponseEntity<>(searchUser, searchUser.getHttpStatus());
    }

    @GetMapping("/verify/email")
    public ResponseEntity<BasicResponse> verifyEmail(@RequestParam String email) {
        BasicResponse verifyResponse;

        if (userService.verifyEmail(email)) {
            verifyResponse = BasicResponse.builder()
                    .code(HttpStatus.CONFLICT.value())
                    .httpStatus(HttpStatus.CONFLICT)
                    .message("이미 사용 중인 이메일 입니다.")
                    .build();

        } else {
            verifyResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("사용이 가능한 이메일 입니다.")
                    .build();
        }

        return new ResponseEntity<>(verifyResponse, verifyResponse.getHttpStatus());
    }

    @GetMapping("/verify/nickname")
    public ResponseEntity<BasicResponse> verifyNickname(@RequestParam String nickname) {
        BasicResponse verifyResponse;
        if (userService.verifyNickname(nickname)) {
            verifyResponse = BasicResponse.builder()
                    .code(HttpStatus.CONFLICT.value())
                    .httpStatus(HttpStatus.CONFLICT)
                    .message("이미 사용 중인 닉네임 입니다.")
                    .build();

        } else {
            verifyResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("사용이 가능한 닉네임 입니다.")
                    .build();
        }

        return new ResponseEntity<>(verifyResponse, verifyResponse.getHttpStatus());
    }

    @GetMapping("/user/friend")
    public ResponseEntity<BasicResponse> searchFriend(@RequestParam String nickname) {
        BasicResponse response;
        if (userService.verifyNickname(nickname)) {
            response = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("존재하는 사용자 입니다. 친구 맺기가 가능합니다.")
                    .build();
        } else {
            response = BasicResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("존재하지 않는 사용자 입니다.")
                    .build();
        }
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @PutMapping("/user/friend")
    public ResponseEntity<BasicResponse> addFriend(@RequestParam Long id, @RequestParam String nickname) {
        userService.addFriends(id, nickname);
        BasicResponse basicResponse;
        basicResponse = BasicResponse.builder()
                .count(HttpStatus.CREATED.value())
                .httpStatus(HttpStatus.CREATED)
                .message("사용자와 친구 맺기가 완료 되었습니다.")
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @GetMapping("/user/profile")
    public ResponseEntity<BasicResponse> searchProfile(@RequestParam Long id) {
        BasicResponse profileResponse;
        profileResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("회원 정보 조회가 완료되었습니다.")
                .count(1)
                .result(Collections.singletonList(userService.searchProfile(id)))
                .build();
        return new ResponseEntity<>(profileResponse, profileResponse.getHttpStatus());
    }

}
