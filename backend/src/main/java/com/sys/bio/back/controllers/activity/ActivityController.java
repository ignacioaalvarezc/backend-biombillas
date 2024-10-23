package com.sys.bio.back.controllers.activity;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.activity.Activity;
import com.sys.bio.back.models.cutting.CutType;
import com.sys.bio.back.services.activity.ActivityService;
import com.sys.bio.back.services.cutting.CutTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
@CrossOrigin("*")
public class ActivityController {

    @Autowired
    private ActivityService actService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Activity> saveActivity(@RequestBody Activity activity) {
        Activity savedActivity = actService.addActivity(activity);
        return ResponseEntity.ok(savedActivity);
    }

    @GetMapping("/id/{activityId}")
    @RequestMapping(method = RequestMethod.GET, path = "/id/{activityId}")
    public Activity listActivityById(@PathVariable("activityId") Long activityId) {
        return actService.getActivity(activityId);
    }

    @GetMapping("/")
    public ResponseEntity<?> activityList() {
        return ResponseEntity.ok(actService.getActivities());
    }

    @PutMapping("/update/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable("activityd") Long activityId,
                                                 @Valid @RequestBody Activity activity) {
        try {
            activity.setActivityId(activityId);
            Activity updatedActivity = actService.updateActivity(activity);
            return ResponseEntity.ok(updatedActivity);
        } catch (Exception e){
            log.error("Error al actualizar la actividad con ID: " + activityId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable("activityId") Long activityId) {
        actService.deleteActivity(activityId);
    }


    /*
    @GetMapping("/mini-factor")
    public ResponseEntity<Double> getMiniCutFactor() {
        Double miniFactor = typeService.getMiniCutFactor();
        if (miniFactor != null) {
            return ResponseEntity.ok(miniFactor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/standard-factor")
    public ResponseEntity<Double> getStandardCutFactor() {
        Double standardFactor = typeService.getStandardCutFactor();
        if (standardFactor != null) {
            return ResponseEntity.ok(standardFactor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/biggy-factor")
    public ResponseEntity<Double> getBiggyCutFactor() {
        Double biggyFactor = typeService.getBiggyCutFactor();
        if (biggyFactor != null) {
            return ResponseEntity.ok(biggyFactor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     */
}
