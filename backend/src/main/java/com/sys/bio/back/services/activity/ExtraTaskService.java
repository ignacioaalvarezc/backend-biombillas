package com.sys.bio.back.services.activity;

import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.cutting.Cutting;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ExtraTaskService {

    ExtraTask addExtraTask(ExtraTask extraTask);
    ExtraTask updateExtraTask(ExtraTask extraTask);
    Set<ExtraTask> getExtraTasks();
    ExtraTask getExtraTask(Long extraTaskId);
    void deleteExtraTask(Long extraTaskId);
    List<ExtraTask> getExtraTasksByDateRange(Date startDate, Date endDate);
    List<ExtraTask> searchByResponsibleName(String name);

    void saveAll(List<ExtraTask> extraTasks);
}
