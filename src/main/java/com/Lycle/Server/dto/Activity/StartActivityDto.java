package com.Lycle.Server.dto.Activity;

import com.Lycle.Server.domain.Activity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StartActivityDto {
    private Long userId;
    private String category;

    public Activity toEntity() {
        Activity build = Activity.builder()
                .userId(userId)
                .category(category)
                .build();
        return build;
    }

    @Builder
    public StartActivityDto(Long userId, String category) {
        this.userId = userId;
        this.category = category;
    }

}
