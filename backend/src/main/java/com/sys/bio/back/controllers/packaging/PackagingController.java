package com.sys.bio.back.controllers.packaging;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.packaging.Packaging;
import com.sys.bio.back.services.packaging.PackagingService;
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
@RequestMapping("/packagings")
@CrossOrigin("*")
public class PackagingController {

    @Autowired
    private PackagingService packagingService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Packaging> savePackaging(@RequestBody Packaging packaging) {
        return ResponseEntity.ok(packagingService.addPackaging(packaging));
    }

    @PutMapping("/update/{packagingId}")
    public ResponseEntity<Packaging> updatePackaging(@PathVariable("packagingId") Long packagingId,
                                                     @Valid @RequestBody Packaging packaging) {
        try {
            packaging.setPackagingId(packagingId);
            Packaging updatedPackaging = packagingService.updatePackaging(packaging);
            return ResponseEntity.ok(updatedPackaging);
        } catch (Exception e) {
            log.error("Error al actualizar el empaquetado con ID: " + packagingId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listPackagings() {
        return ResponseEntity.ok(packagingService.getPackagings());
    }

    @GetMapping("/id/{packagingId}")
    public Packaging listPackaging(@PathVariable("packagingId") Long packagingId) {
        return packagingService.getPackaging(packagingId);
    }


    @DeleteMapping("/{packagingId}")
    public void deletePackaging(@PathVariable("packagingId") Long packagingId) {
        packagingService.deletePackaging(packagingId);
    }


    @GetMapping("/date-filter")
    public ResponseEntity<List<Packaging>> getFilteredPackagings(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Packaging> filteredPackagings = packagingService.getPackagingsByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredPackagings, HttpStatus.OK);
    }


    @GetMapping("/search")

    public ResponseEntity<List<Packaging>> searchByResponsibleName(
            @RequestParam(value = "name", required = false) String name) {
        List<Packaging> packagings = packagingService.searchByResponsibleName(name);
        return new ResponseEntity<>(packagings, HttpStatus.OK);
    }

}

