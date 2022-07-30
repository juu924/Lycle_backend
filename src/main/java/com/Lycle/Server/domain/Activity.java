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

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private boolean finishChecked;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private boolean rewardChecked;

    @Builder
    public Activity(Long id, Long userId, String category, boolean finishChecked, boolean rewardChecked) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.finishChecked = finishChecked;
        this.rewardChecked = rewardChecked;
    }

    public void update(boolean finishChecked, boolean rewardChecked){
        this.finishChecked = finishChecked;
        this.rewardChecked = rewardChecked;
    }


}
