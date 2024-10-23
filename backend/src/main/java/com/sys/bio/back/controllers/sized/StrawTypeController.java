package com.sys.bio.back.controllers.sized;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.sized.StrawType;
import com.sys.bio.back.services.sized.StrawTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/strawTypes")
@CrossOrigin("*")
public class StrawTypeController {

    @Autowired
    private StrawTypeService strawTypeService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<StrawType> saveStrawType(@RequestBody StrawType strawType) {
        StrawType savedStrawType = strawTypeService.addStrawType(strawType);
        return ResponseEntity.ok(savedStrawType);
    }

    @GetMapping("/id/{strawTypeId}")
    @RequestMapping(method = RequestMethod.GET, path = "/id/{strawTypeId}")
    public StrawType listStrawTypeById(@PathVariable("strawTypeId") Long strawTypeId) {
        return strawTypeService.getStrawType(strawTypeId);
    }

    @GetMapping("/")
    public ResponseEntity<?> strawTypeList() {
        return ResponseEntity.ok(strawTypeService.getStrawTypes());
    }

    @PutMapping("/update/{strawTypeId}")
    public ResponseEntity<StrawType> updateStrawType(@PathVariable("strawTypeId") Long strawTypeId,
                                                         @Valid @RequestBody StrawType strawType) {
        try {
            strawType.setStrawTypeId(strawTypeId);
            StrawType updatedStrawType = strawTypeService.updateStrawType(strawType);
            return ResponseEntity.ok(updatedStrawType);
        } catch (Exception e){
            log.error("Error al actualizar el tipo de bombilla con ID: " + strawTypeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{strawTypeId}")
    public void deleteStrawType(@PathVariable("strawTypeId") Long strawTypeId) {
        strawTypeService.deleteStrawType(strawTypeId);
    }

    @PutMapping("/toggle-status/{strawTypeId}")
    public ResponseEntity<Map<String, String>> toggleStrawTypeStatus(@PathVariable("strawTypeId") Long strawTypeId,
                                                          @RequestBody Map<String, Boolean> statusMap) {
        boolean newStatus = statusMap.get("enabled");
        strawTypeService.toggleStrawTypeStatus(strawTypeId, newStatus);
        Map<String, String> response = new HashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StrawType>> list() {
        List<StrawType> list = strawTypeService.findAll();
        return new ResponseEntity<List<StrawType>>(list, HttpStatus.OK);
    }
}
