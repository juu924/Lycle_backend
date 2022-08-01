package com.Lycle.Server.dto.User;

import com.Lycle.Server.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserJoinDto {
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "닉네임을 입력해주세요")
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
