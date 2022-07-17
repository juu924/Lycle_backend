package com.Lycle.Server.controller;

import com.Lycle.Server.dto.User.UserJoinDto;
import com.Lycle.Server.dto.User.UserLoginDto;
import com.Lycle.Server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/join")
    public Long joinUser(UserJoinDto userJoinDto){
       return userService.saveUser(userJoinDto);
    }

    @GetMapping("/login")
    public Long searchUser(UserLoginDto userLoginDto){
        return userService.searchUser(userLoginDto);
    }

}
