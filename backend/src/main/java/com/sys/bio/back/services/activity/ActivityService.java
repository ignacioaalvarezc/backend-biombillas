package com.sys.bio.back.services.activity;

import com.sys.bio.back.models.activity.Activity;
import com.sys.bio.back.models.cutting.CutType;

import java.util.List;
import java.util.Set;

public interface ActivityService {

    Activity addActivity(Activity activity);

    Activity updateActivity(Activity activity);

    Set<Activity> getActivities();

    public List<Activity> findAll();

    Activity getActivity(Long activityId);

    void deleteActivity(Long activityId);

/*
TO GET FACTOR
    Double getMiniCutFactor();
    Double getStandardCutFactor();
    Double getBiggyCutFactor();

 */
}
