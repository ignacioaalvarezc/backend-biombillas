package com.sys.bio.back.repositories.activity;

import com.sys.bio.back.models.activity.Activity;
import com.sys.bio.back.models.cutting.CutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a.activityId FROM Activity a")
    List<Long> findAllIds();

    Optional<Activity> findByName(String name);
}
