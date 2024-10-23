package com.sys.bio.back.controllers.packaging;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.models.packaging.Provider;
import com.sys.bio.back.services.packaging.BoxNameService;
import com.sys.bio.back.services.packaging.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/boxNames")
@CrossOrigin("*")
public class BoxNameController {

    @Autowired
    private BoxNameService nameService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<BoxName> saveBoxName(@RequestBody BoxName boxName) {
        return ResponseEntity.ok(nameService.addBoxName(boxName));
    }

    @PutMapping("/update/{boxNameId}")
    public ResponseEntity<BoxName> updateBoxName(@PathVariable("boxNameId") Long boxNameId,
                                                   @Valid @RequestBody BoxName boxName) {
        try {
            boxName.setBoxNameId(boxNameId);
            BoxName updatedBoxName = nameService.updateBoxName(boxName);
            return ResponseEntity.ok(updatedBoxName);
        } catch (Exception e) {
            log.error("Error al actualizar el nombre del estuche con ID: " + boxNameId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listBoxNames() {
        return ResponseEntity.ok(nameService.getBoxNames());
    }

    @GetMapping("/id/{boxNameId}")
    public BoxName listBoxName(@PathVariable("boxNameId") Long boxNameId) {
        return nameService.getBoxName(boxNameId);
    }


    @DeleteMapping("/{boxNameId}")
    public void deleteBoxName(@PathVariable("boxNameId") Long boxNameId) {
        nameService.deleteBoxName(boxNameId);
    }

    @GetMapping("/providers/{providerId}/boxNames")
    public List<BoxName> getBoxNamesByProvider(@PathVariable Long providerId) {
        return nameService.getBoxNamesByProvider(providerId);
    }
}
