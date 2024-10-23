package com.sys.bio.back.controllers.cutting;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.cutting.CutType;
import com.sys.bio.back.services.cutting.CutTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cutTypes")
@CrossOrigin("*")
public class CutTypeController {

    @Autowired
    private CutTypeService typeService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<CutType> saveCutType(@RequestBody CutType cutType) {
        CutType savedCutType = typeService.addCutType(cutType);
        return ResponseEntity.ok(savedCutType);
    }

    @GetMapping("/id/{cutTypeId}")
    @RequestMapping(method = RequestMethod.GET, path = "/id/{cutTypeId}")
    public CutType listCutTypeById(@PathVariable("cutTypeId") Long cutTypeId) {
        return typeService.getCutType(cutTypeId);
    }

    @GetMapping("/")
    public ResponseEntity<?> cutTypeList() {
        return ResponseEntity.ok(typeService.getCutTypes());
    }

    @PutMapping("/update/{cutTypeId}")
    public ResponseEntity<CutType> updateCutType(@PathVariable("cutTypeId") Long cutTypeId,
                                                     @Valid @RequestBody CutType cutType) {
        try {
            cutType.setCutTypeId(cutTypeId);
            CutType updatedCutType = typeService.updateCutType(cutType);
            return ResponseEntity.ok(updatedCutType);
        } catch (Exception e){
            log.error("Error al actualizar la caja de corte con ID: " + cutTypeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /*

    @DeleteMapping("/{cutTypeId}")
    public void deleteCutType(@PathVariable("cutTypeId") Long cutTypeId) {
        typeService.deleteCutType(cutTypeId);
    }

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
