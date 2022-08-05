package com.Lycle.Server.dto.Activity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FinishActivityDto {
    private Long id;
    private Long userId;
    private String category;
    private String activityTime;
    private boolean finishChecked;
    private boolean rewardChecked;

    @Builder
    public FinishActivityDto(Long id,Long userId,String category,String activityTime ,boolean finishChecked, boolean rewardChecked){
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.activityTime = activityTime;
        this.finishChecked = finishChecked;
        this.rewardChecked = rewardChecked;
    }

}
