package com.Lycle.Server.service;

import com.Lycle.Server.domain.Activity;
import com.Lycle.Server.domain.jpa.ActivityRepository;
import com.Lycle.Server.dto.Activity.FinishActivityDto;
import com.Lycle.Server.dto.Activity.SearchActivityWrapper;
import com.Lycle.Server.dto.Activity.StartActivityDto;
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
    public Long startActivity(StartActivityDto startActivityDto){
       return activityRepository.save(startActivityDto.toEntity()).getId();
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
    public List<SearchActivityWrapper> searchActivity(Long userId){
        List<Activity> activityList = activityRepository.findActivitiesByUserId(userId);
        List<SearchActivityWrapper> searchActivityList = new ArrayList<>();
        for(int i = 0; i < activityList.size(); i++){
            //yy/mm/dd HH:mm 에서 먼저 날짜와 시간을 분리하여 챌린지 일자로 지정
            StringTokenizer start = new StringTokenizer(activityList.get(i).getCreatedDate());
            searchActivityList.get(i).setActivityDate(start.nextToken());
            //활동 이름과 챌린지 활동 시간, 챌린지 완료 여부 불러오기
            searchActivityList.get(i).setActivityName(activityList.get(i).getCategory());
            searchActivityList.get(i).setActivityTime(activityList.get(i).getActivityTime());
            searchActivityList.get(i).setFinishedChecked(activityList.get(i).isFinishChecked());
        }
        return searchActivityList;
    }

}
