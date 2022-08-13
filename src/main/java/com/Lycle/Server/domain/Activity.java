package com.Lycle.Server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Activity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 100, nullable = false)
    private String category;

    @Column(length = 50, nullable = true)
    private String activityTime;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private boolean finishChecked;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private boolean rewardChecked;

    @Builder
    public Activity(Long id, Long userId, String category, String activityTime ,boolean finishChecked, boolean rewardChecked) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.activityTime = activityTime;
        this.finishChecked = finishChecked;
        this.rewardChecked = rewardChecked;
    }

}
