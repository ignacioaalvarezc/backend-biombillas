package com.sys.bio.back.controllers.packaging;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.packaging.Package;
import com.sys.bio.back.services.packaging.PackageService;
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
@RequestMapping("/packages")
@CrossOrigin("*")
public class PackageController {

    @Autowired
    private PackageService packService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Package> savePackage(@RequestBody Package pack) {
        return ResponseEntity.ok(packService.addPackage(pack));
    }

    @PutMapping("/update/{packageId}")
    public ResponseEntity<Package> updatePackage(@PathVariable("packageId") Long packageId,
                                               @Valid @RequestBody Package pack) {
        try {
            pack.setPackageId(packageId);
            Package updatedPackage = packService.updatePackage(pack);
            return ResponseEntity.ok(updatedPackage);
        } catch (Exception e) {
            log.error("Error al actualizar el estuchado con ID: " + packageId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listPackages() {
        return ResponseEntity.ok(packService.getPackages());
    }

    @GetMapping("/id/{packageId}")
    public Package listPackage(@PathVariable("packageId") Long packageId) {
        return packService.getPackage(packageId);
    }


    @DeleteMapping("/{packageId}")
    public void deletePackage(@PathVariable("packageId") Long packageId) {
        packService.deletePackage(packageId);
    }

    @GetMapping("/byPackaging/{packagingId}")
    public ResponseEntity<List<Package>> getPackagesByPackagingId(@PathVariable Long packagingId) {
        List<Package> packages = packService.getPackagesByPackagingId(packagingId);
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }


    @GetMapping("/date-filter")
    public ResponseEntity<List<Package>> getFilteredPackages(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Package> filteredPackages = packService.getPackagesByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredPackages, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Package>> searchByResponsibleName(
            @RequestParam(value = "name", required = false) String name) {
        List<Package> packages = packService.searchByResponsibleName(name);
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAllPackages(@RequestBody List<Package> packages) {
        try {
            packService.saveAll(packages);
            return ResponseEntity.ok("Packages has saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving packages: " + e.getMessage());
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + ex.getMessage());
    }
}
