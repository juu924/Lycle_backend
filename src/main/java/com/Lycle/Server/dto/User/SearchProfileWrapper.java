package com.Lycle.Server.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class SearchProfileWrapper {
    private String nickname;
    private Long totalTime;
}
