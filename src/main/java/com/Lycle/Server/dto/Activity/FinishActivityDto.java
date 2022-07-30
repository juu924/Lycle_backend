package com.Lycle.Server.dto.Activity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FinishActivityDto {
    private Long id;
    private Long userId;
    private boolean finishChecked;
    private boolean rewardChecked;

    @Builder
    public FinishActivityDto(Long id,Long userId,boolean finishChecked, boolean rewardChecked){
        this.id = id;
        this.userId = userId;
        this.finishChecked = finishChecked;
        this.rewardChecked = rewardChecked;
    }

}
