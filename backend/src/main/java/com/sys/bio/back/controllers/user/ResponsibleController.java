package com.sys.bio.back.controllers.user;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.user.Responsible;
import com.sys.bio.back.services.user.ResponsibleService;
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
@RequestMapping("/responsibles")
@CrossOrigin("*")
public class ResponsibleController {

    @Autowired
    private ResponsibleService responsibleService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Responsible> saveResponsible(@RequestBody Responsible responsible) {
        Responsible savedResponsible = responsibleService.addResponsible(responsible);
        return ResponseEntity.ok(savedResponsible);
    }

    @GetMapping("/id/{responsibleId}")
    @RequestMapping(method = RequestMethod.GET, path = "/id/{responsibleId}")
    public Responsible listResponsibleById(@PathVariable("responsibleId") Long responsibleId) {
        return responsibleService.getResponsible(responsibleId);
    }

    @GetMapping("/")
    public ResponseEntity<?> responsibleList() {
        return ResponseEntity.ok(responsibleService.getResponsibles());
    }

    @PutMapping("/update/{responsibleId}")
    public ResponseEntity<Responsible> updateResponsible(@PathVariable("responsibleId") Long responsibleId,
                                         @Valid @RequestBody Responsible responsible) {
        try {
            responsible.setResponsibleId(responsibleId);
            Responsible updatedResponsible = responsibleService.updateResponsible(responsible);
            return ResponseEntity.ok(updatedResponsible);
        } catch (Exception e){
            log.error("Error al actualizar el responsable con ID: " + responsibleId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{responsibleId}")
    public void deleteResponsible(@PathVariable("responsibleId") Long responsibleId) {
        responsibleService.deleteResponsible(responsibleId);
    }

    @PutMapping("/toggle-status/{responsibleId}")
    public ResponseEntity<Map<String, String>> toggleResponsibleStatus(@PathVariable("responsibleId") Long responsibleId,
                                                   @RequestBody Map<String, Boolean> statusMap) {
        boolean newStatus = statusMap.get("enabled");
        responsibleService.toggleResponsibleStatus(responsibleId, newStatus);
        Map<String, String> response = new HashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Responsible>> list() {
        List<Responsible> list = responsibleService.findAll();
        return new ResponseEntity<List<Responsible>>(list, HttpStatus.OK);
    }
}
