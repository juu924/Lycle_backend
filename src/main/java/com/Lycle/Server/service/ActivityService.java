package com.Lycle.Server.service;

import com.Lycle.Server.domain.Activity;
import com.Lycle.Server.domain.jpa.ActivityRepository;
import com.Lycle.Server.dto.Activity.FinishActivityDto;
import com.Lycle.Server.dto.Activity.SearchActivityWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    @Transactional
    public Long startActivity(Long id){
       return activityRepository.save(Activity.builder()
               .userId(id)
               .build()).getId();
    }

    @Transactional
    public Activity finishActivity(FinishActivityDto finishActivityDto){
        Long id = finishActivityDto.getId();
        Activity activity = activityRepository.findById(id).orElseThrow(
                () ->new IllegalArgumentException("존재 하지 않는 챌린지 입니다."));

        activity.update(finishActivityDto.getCategory(), finishActivityDto.getActivityTime(),
                finishActivityDto.isFinishChecked(), finishActivityDto.isRewardChecked());
        return activity;
    }

    @Transactional(readOnly = true)
    public List<SearchActivityWrapper> searchActivity(Long id){
        return activityRepository.findActivitiesByUserIdOrderByCreatedDateDesc(id);
    }

}
