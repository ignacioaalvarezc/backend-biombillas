package com.sys.bio.back.controllers.activity;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.services.activity.ExtraTaskService;
import com.sys.bio.back.services.cutting.CuttingService;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/extraTasks")
@CrossOrigin("*")
public class ExtraTaskController {

    @Autowired
    private ExtraTaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/")
    public ResponseEntity<ExtraTask> saveExtraTask(@RequestBody ExtraTask extraTask) {
        return ResponseEntity.ok(taskService.addExtraTask(extraTask));
    }


    @PutMapping("/update/{extraTaskId}")
    public ResponseEntity<ExtraTask> updateExtraTask(@PathVariable("extraTaskId") Long extraTaskId,
                                                 @Valid @RequestBody ExtraTask extraTask) {
        try {
            extraTask.setExtraTaskId(extraTaskId);
            ExtraTask updatedExtraTask = taskService.updateExtraTask(extraTask);
            return ResponseEntity.ok(updatedExtraTask);
        } catch (Exception e) {
            log.error("Error al actualizar el turno extra con ID: " + extraTaskId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getExtraTasks() {
        return ResponseEntity.ok(taskService.getExtraTasks());
    }

    @GetMapping("/id/{extraTaskId}")
    public ExtraTask getExtraTask(@PathVariable("extraTaskId") Long extraTaskId) {
        return taskService.getExtraTask(extraTaskId);
    }

    @DeleteMapping("/{extraTaskId}")
    public void deleteExtraTask(@PathVariable("extraTaskId") Long extraTaskId) {
        taskService.deleteExtraTask(extraTaskId);
    }

    @GetMapping("/date-filter")
    public ResponseEntity<List<ExtraTask>> getFilteredExtraTasks(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<ExtraTask> filteredExtraTasks = taskService.getExtraTasksByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredExtraTasks, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExtraTask>> searchByResponsibleName(
            @RequestParam(value = "name", required = false) String name) {
        List<ExtraTask> extraTasks = taskService.searchByResponsibleName(name);
        return new ResponseEntity<>(extraTasks, HttpStatus.OK);
    }

    @PostMapping("/saveAllExtraTasks")
    public ResponseEntity<?> saveAllExtraTasks(@RequestBody List<ExtraTask> extraTasks) {
        try {
            taskService.saveAll(extraTasks);
            return ResponseEntity.ok("Extra tasks has saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving extra tasks: " + e.getMessage());
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + ex.getMessage());
    }
}
