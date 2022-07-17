package com.Lycle.Server.dto.User;

import com.Lycle.Server.domain.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserJoinDto {
    private String email;
    private String password;
    private String nickname;

    public User toEntity() {
       User user = User.builder()
               .email(email)
               .password(password)
               .nickname(nickname)
               .build();
       return user;
    }

    @Builder
    public UserJoinDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

}
