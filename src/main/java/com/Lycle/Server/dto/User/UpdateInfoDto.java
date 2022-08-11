package com.Lycle.Server.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateInfoDto {
    private String nickname;
    private String password;

    @Builder
    public UpdateInfoDto(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }
}
