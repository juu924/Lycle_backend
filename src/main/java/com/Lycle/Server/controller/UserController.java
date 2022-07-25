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
        BasicResponse joinUserResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.CREATED).message("회원가입이 완료되었습니다.").build();
        return new ResponseEntity<>(joinUserResponse, joinUserResponse.getHttpStatus());

    }

    @GetMapping("/login")
    public ResponseEntity<BasicResponse> searchUser(@RequestBody UserLoginDto userLoginDto) {
        BasicResponse searchUser = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK).message("로그인에 성공했습니다.").count(1).result(Collections.singletonList(userService.searchUser(userLoginDto))).build();
        return new ResponseEntity<>(searchUser, searchUser.getHttpStatus());
    }

    @GetMapping("/verify/email")
    public ResponseEntity<BasicResponse> verifyEmail(@RequestParam String email) {
        BasicResponse verifyResponse;

        if (userService.verifyEmail(email) == true) {
            verifyResponse = BasicResponse.builder().code(HttpStatus.CONFLICT.value()).httpStatus(HttpStatus.CONFLICT).message("이미 사용 중인 이메일 입니다.").build();

        } else {
            verifyResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK).message("사용이 가능한 이메일 입니다.").build();
        }

        return new ResponseEntity<>(verifyResponse, verifyResponse.getHttpStatus());
    }

    @GetMapping("/verify/nickname")
    public ResponseEntity<BasicResponse> verifyNickname(@RequestParam String nickname) {
        BasicResponse verifyResponse;
        if (userService.verifyNickname(nickname) == true) {
            verifyResponse = BasicResponse.builder().code(HttpStatus.CONFLICT.value()).httpStatus(HttpStatus.CONFLICT).message("이미 사용 중인 이메일 입니다.").build();

        } else {
            verifyResponse = BasicResponse.builder().code(HttpStatus.OK.value()).httpStatus(HttpStatus.OK).message("사용이 가능한 닉네임 입니다.").build();
        }

        return new ResponseEntity<>(verifyResponse, verifyResponse.getHttpStatus());
    }


}
