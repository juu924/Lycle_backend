package com.Lycle.Server.controller;

import com.Lycle.Server.dto.Activity.FinishActivityDto;
import com.Lycle.Server.dto.Activity.StartActivityDto;
import com.Lycle.Server.dto.BasicResponse;
import com.Lycle.Server.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    //운동 시작 시간 기록
    @PostMapping("/activity")
    public ResponseEntity<BasicResponse> startActivity(@RequestBody StartActivityDto startActivityDto){
        activityService.startActivity(startActivityDto);
        BasicResponse activityResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.CREATED)
                .message("챌린지 시작 시간이 기록되었습니다.")
                .build();
        return new ResponseEntity<>(activityResponse,activityResponse.getHttpStatus());
    }

    //운동 중단 시간 기록
    @PutMapping("/activity")
    public ResponseEntity<BasicResponse> finishActivity(@RequestBody FinishActivityDto finishActivityDto){
        BasicResponse activityResponse = BasicResponse.builder()
                .code(HttpStatus.CREATED.value())
                .httpStatus(HttpStatus.CREATED)
                .message("챌린지 종료 시간이 기록되었습니다.")
                .count(1)
                .result(Collections.singletonList(activityService.finishActivity(finishActivityDto)))
                .build();
        return new ResponseEntity<>(activityResponse,activityResponse.getHttpStatus());
    }

    @GetMapping("/activity/{userId}")
    public ResponseEntity<BasicResponse> searchAllAcitivity(@PathVariable Long userId){
        BasicResponse allActivity = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("챌린지 조회가 완료 되었습니다.")
                .count(activityService.searchActivity(userId).size())
                .result(Collections.singletonList(activityService.searchActivity(userId)))
                .build();

        return new ResponseEntity<>(allActivity, allActivity.getHttpStatus());
    }

}
