package com.Lycle.Server.dto.User;

import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginDto {
   @NotBlank(message = "이메일 주소를 입력해주세요.")
   private String email;
   @NotBlank(message = "비밀번호를 입력해주세요.")
   private String password;

   public UserLoginDto(String email, String password){
      this.email = email;
      this.password = password;
   }

}
