package com.sys.bio.back.controllers.sanitized;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.sanitized.SanitizedBox;
import com.sys.bio.back.models.sanitized.UpdateRequest;
import com.sys.bio.back.services.sanitized.SanitizedBoxService;
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
@RequestMapping("/sanitizedBoxes")
@CrossOrigin("*")
public class SanitizedBoxController {

    private final SanitizedBoxService boxService;



    @Autowired
    public SanitizedBoxController(SanitizedBoxService boxService) {
        this.boxService = boxService;
    }

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<SanitizedBox> saveSanitizedBox(@RequestBody SanitizedBox sanitizedBox) {
        SanitizedBox savedSanitizedBox = boxService.addSanitizedBox(sanitizedBox);
        if ("HUMEDO".equals(savedSanitizedBox.getState())) {
        }
        return new ResponseEntity<>(savedSanitizedBox, HttpStatus.CREATED);
    }
/*
    @PutMapping("/update/{sanitatedBasketId}")
    public ResponseEntity<SanitizedBox> updateSanitatedBasket(@PathVariable("sanitatedBasketId") Long sanitatedBasketId,
                                                                 @Valid @RequestBody SanitizedBox sanitatedBasket) {
        try {
            sanitatedBasket.setSanitatedBasketId(sanitatedBasketId);
            SanitizedBox updatedSanitatedBasket = sanitizedBoxService.updateSanitatedBasket(sanitatedBasket);
            return ResponseEntity.ok(updatedSanitatedBasket);
        } catch (Exception e) {
            log.error("Error al actualizar el canasto de sanitización con ID: " + sanitatedBasketId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

 */

    @PutMapping("/update/{sanitizedBoxId}")
    public ResponseEntity<SanitizedBox> updateBasket(@PathVariable("sanitizedBoxId") Long sanitizedBoxId,
                                                     @Valid @RequestBody SanitizedBox sanitizedBox) {
        return ResponseEntity.ok(boxService.addSanitizedBox(sanitizedBox));
    }

    @GetMapping("/")
    public ResponseEntity<?> listSanitizedBoxes() {
        return ResponseEntity.ok(boxService.getSanitizedBoxes());
    }

    @GetMapping("/id/{sanitizedBoxId}")
    public SanitizedBox listSanitizedBox(@PathVariable("sanitizedBoxId") Long sanitizedBoxId) {
        return boxService.getSanitizedBox(sanitizedBoxId);
    }


    @DeleteMapping("/{sanitizedBoxId}")
    public void deleteSanitizedBox(@PathVariable("sanitizedBoxId") Long sanitizedBoxId) {
        boxService.deleteSanitizedBoxId(sanitizedBoxId);
    }


    @GetMapping("/date-filter")
    public ResponseEntity<List<SanitizedBox>> getFilteredSanitizedBoxes(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<SanitizedBox> filteredSanitizedBoxes = boxService.getSanitizedBoxesByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredSanitizedBoxes, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<SanitizedBox>> searchByResponsibleName(
            @RequestParam(value = "name", required = false) String name) {
        List<SanitizedBox> sanitizedBoxes = boxService.searchByResponsibleName(name);
        return new ResponseEntity<>(sanitizedBoxes, HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAllSanitizedBoxes(@RequestBody List<SanitizedBox> sanitizedBoxes) {
        try {
            boxService.saveAll(sanitizedBoxes);
            return ResponseEntity.ok("Sanitized boxes has saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving cut boxes: " + e.getMessage());
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + ex.getMessage());
    }

    @PostMapping("/updateAll")
    public ResponseEntity<?> updateAllSizedBoxes(@RequestBody UpdateRequest updateRequest) {
        try {
            boxService.updateAll(updateRequest.getIds(), updateRequest.getSanitizedBoxes());
            return ResponseEntity.ok("Sized boxes have been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating sized boxes: " + e.getMessage());
        }
    }

    @GetMapping("/sanitized/{sanitizedId}")
    public List<SanitizedBox> getSanitizedBoxesBySanitizedId(@PathVariable Long sanitizedId) {
        return boxService.getSanitizedBoxesBySanitizedId(sanitizedId);
    }

    @GetMapping("/bySanitized/{sanitizedId}")
    public ResponseEntity<List<SanitizedBox>> getSanitizedBoxesBySanitizedForVisual(@PathVariable Long sanitizedId) {
        List<SanitizedBox> sanitizedBoxes = boxService.getSanitizedBoxesBySanitizedId(sanitizedId);
        return new ResponseEntity<>(sanitizedBoxes, HttpStatus.OK);
    }
}
