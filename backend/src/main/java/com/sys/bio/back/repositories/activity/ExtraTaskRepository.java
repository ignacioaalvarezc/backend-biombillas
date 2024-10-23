package com.sys.bio.back.repositories.activity;

import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.cutting.Cutting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ExtraTaskRepository extends JpaRepository<ExtraTask, Long> {

    @Query("SELECT e.extraTaskId FROM ExtraTask e")
    List<Long> findAllIds();

    List<ExtraTask> findByDateBetween(Date startDate, Date endDate);

    List<ExtraTask> findByResponsibleNameContainingIgnoreCase(String name);
}
