package com.sys.bio.back.controllers.sanitized;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.sanitized.Sanitized;
import com.sys.bio.back.services.sanitized.SanitizedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sanitizeds")
@CrossOrigin("*")
public class SanitizedController {

    @Autowired
    private SanitizedService sanitizedService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Sanitized> saveSanitized(@RequestBody Sanitized sanitized) {
        return ResponseEntity.ok(sanitizedService.addSanitized(sanitized));
    }

    @PutMapping("/update/{sanitizedId}")
    public ResponseEntity<Sanitized> updateSanitized(@PathVariable("sanitizedId") Long sanitizedId,
                                                     @Valid @RequestBody Sanitized sanitized) {
        try {
            sanitized.setSanitizedId(sanitizedId);
            Sanitized updatedSanitized = sanitizedService.updateSanitized(sanitized);
            return ResponseEntity.ok(updatedSanitized);
        } catch (Exception e) {
            log.error("Error al actualizar la sanitización con ID: " + sanitizedId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listSanitizeds() {
        return ResponseEntity.ok(sanitizedService.getSanitizeds());
    }

    @GetMapping("/id/{sanitizedId}")
    public Sanitized listSanitized(@PathVariable("sanitizedId") Long sanitizedId) {
        return sanitizedService.getSanitized(sanitizedId);
    }


    @DeleteMapping("/{sanitizedId}")
    public void deleteSanitized(@PathVariable("sanitizedId") Long sanitizedId) {
        sanitizedService.deleteSanitized(sanitizedId);
    }


    @GetMapping("/date-filter")
    public ResponseEntity<List<Sanitized>> getFilteredSanitizeds(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Sanitized> filteredSanitizeds = sanitizedService.getSanitizedsByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredSanitizeds, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Sanitized>> searchByResponsibleName(
            @RequestParam(value = "name", required = false) String name) {
        List<Sanitized> sanitizeds = sanitizedService.searchByResponsibleName(name);
        return new ResponseEntity<>(sanitizeds, HttpStatus.OK);
    }
}
