package com.Lycle.Server.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchUserWrapper {
    private Long id;
    private String email;
    private String nickname;
}
