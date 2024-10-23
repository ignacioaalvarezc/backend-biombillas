package com.sys.bio.back.controllers.packaging;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.packaging.Package;
import com.sys.bio.back.models.packaging.Provider;
import com.sys.bio.back.services.packaging.PackageService;
import com.sys.bio.back.services.packaging.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/providers")
@CrossOrigin("*")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Provider> saveProvider(@RequestBody Provider provider) {
        return ResponseEntity.ok(providerService.addProvider(provider));
    }

    @PutMapping("/update/{providerId}")
    public ResponseEntity<Provider> updateProvider(@PathVariable("providerId") Long providerId,
                                                 @Valid @RequestBody Provider provider) {
        try {
            provider.setProviderId(providerId);
            Provider updatedProvider = providerService.updateProvider(provider);
            return ResponseEntity.ok(updatedProvider);
        } catch (Exception e) {
            log.error("Error al actualizar el proveedor con ID: " + providerId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listProviders() {
        return ResponseEntity.ok(providerService.getProviders());
    }

    @GetMapping("/id/{providerId}")
    public Provider listProvider(@PathVariable("providerId") Long providerId) {
        return providerService.getProvider(providerId);
    }


    @DeleteMapping("/{providerId}")
    public void deleteProvider(@PathVariable("providerId") Long providerId) {
        providerService.deleteProvider(providerId);
    }

}
