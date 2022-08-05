package com.Lycle.Server.dto.Activity;

import com.Lycle.Server.domain.Activity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StartActivityDto {
    private Long userId;

    public Activity toEntity() {
        Activity build = Activity.builder()
                .userId(userId)
                .build();
        return build;
    }

    @Builder
    public StartActivityDto(Long userId) {
        this.userId = userId;
    }

}
