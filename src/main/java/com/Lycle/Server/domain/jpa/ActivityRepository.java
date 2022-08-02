package com.Lycle.Server.domain.jpa;

import com.Lycle.Server.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>{
    @Query(value = "SELECT a.createdDate, a.category, a.finishChecked " +
            "FROM Activity a WHERE a.userId =: userId order by a.createdDate desc", nativeQuery = true)
    List<Activity> findActivitiesByUserId(Long userId);
}
