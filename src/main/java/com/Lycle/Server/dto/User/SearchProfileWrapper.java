package com.Lycle.Server.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class SearchProfileWrapper {
    private String nickname;
    private Long totalTime;
}
