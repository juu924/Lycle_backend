package com.Lycle.Server.dto.Activity;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class SearchActivityWrapper {
    private String activityDate;
    private String activityName;
    private String activityTime;
    private boolean finishedChecked;
}
