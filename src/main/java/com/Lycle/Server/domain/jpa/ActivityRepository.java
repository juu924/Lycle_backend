package com.Lycle.Server.domain.jpa;

import com.Lycle.Server.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
