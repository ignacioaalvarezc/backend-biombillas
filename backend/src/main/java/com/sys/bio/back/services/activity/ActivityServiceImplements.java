package com.sys.bio.back.services.activity;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.activity.Activity;
import com.sys.bio.back.models.cutting.CutType;
import com.sys.bio.back.repositories.activity.ActivityRepository;
import com.sys.bio.back.repositories.cutting.CutTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ActivityServiceImplements implements ActivityService{

    @Autowired
    private ActivityRepository actRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    @Override
    public Activity addActivity(Activity activity) {
        return actRepo.save(activity);
    }
    @Override
    public Activity updateActivity(Activity activity) {
        return actRepo.save(activity);
    }

    @Override
    public Set<Activity> getActivities() {
        return new LinkedHashSet<>(actRepo.findAll());
    }

    @Override
    public Activity getActivity(Long activityId) {
        return actRepo.findById(activityId).get();
    }

    @Override
    public void deleteActivity(Long activityId) {
        Activity activity = new Activity();
        activity.setActivityId(activityId);
        actRepo.delete(activity);
    }

    @Override
    public List<Activity> findAll() {
        return  actRepo.findAll();
    }

    /*
    @Override
    public Double getMiniCutFactor() {
        Optional<CutType> miniParameter = typeRepo.findByName("Mini");
        return miniParameter.map(CutType::getFactor).orElse(null);
    }

    @Override
    public Double getStandardCutFactor() {
        Optional<CutType> standardParameter = typeRepo.findByName("Standard");
        return standardParameter.map(CutType::getFactor).orElse(null);
    }

    @Override
    public Double getBiggyCutFactor() {
        Optional<CutType> biggyParameter = typeRepo.findByName("Biggy");
        return biggyParameter.map(CutType::getFactor).orElse(null);
    }

     */
}
