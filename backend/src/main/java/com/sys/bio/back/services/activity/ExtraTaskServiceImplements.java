package com.sys.bio.back.services.activity;

import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.repositories.activity.ExtraTaskRepository;
import com.sys.bio.back.repositories.cutting.CuttingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExtraTaskServiceImplements implements ExtraTaskService {

    @Autowired
    private ExtraTaskRepository taskRepo;

    @Override
    public ExtraTask addExtraTask(ExtraTask extraTask) {
        return taskRepo.save(extraTask);
    }

    @Override
    public ExtraTask updateExtraTask(ExtraTask extraTask) {
        return taskRepo.save(extraTask);
    }

    @Override
    public Set<ExtraTask> getExtraTasks() {
        return new LinkedHashSet<>(taskRepo.findAll());
    }

    @Override
    public ExtraTask getExtraTask(Long extraTaskId) {
        return taskRepo.findById(extraTaskId).get();
    }

    @Override
    public void deleteExtraTask(Long extraTaskId) {
        ExtraTask extraTask = new ExtraTask();
        extraTask.setExtraTaskId(extraTaskId);
        taskRepo.delete(extraTask);
    }

    @Override
    public List<ExtraTask> getExtraTasksByDateRange(Date startDate, Date endDate) {
        return taskRepo.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<ExtraTask> searchByResponsibleName(String name) {
        if (name != null) {
            return taskRepo.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void saveAll(List<ExtraTask> extraTasks) { taskRepo.saveAll(extraTasks); }


}
