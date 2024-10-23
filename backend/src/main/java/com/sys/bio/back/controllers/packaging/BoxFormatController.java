package com.sys.bio.back.controllers.packaging;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.packaging.BoxFormat;
import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.services.packaging.BoxFormatService;
import com.sys.bio.back.services.packaging.BoxNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/boxFormats")
@CrossOrigin("*")
public class BoxFormatController {

    @Autowired
    private BoxFormatService formatService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<BoxFormat> saveBoxFormat(@RequestBody BoxFormat boxFormat) {
        return ResponseEntity.ok(formatService.addBoxFormat(boxFormat));
    }

    @PutMapping("/update/{boxFormatId}")
    public ResponseEntity<BoxFormat> updateBoxFormat(@PathVariable("boxFormatId") Long boxFormatId,
                                                 @Valid @RequestBody BoxFormat boxFormat) {
        try {
            boxFormat.setBoxFormatId(boxFormatId);
            BoxFormat updatedBoxFormat = formatService.updateBoxFormat(boxFormat);
            return ResponseEntity.ok(updatedBoxFormat);
        } catch (Exception e) {
            log.error("Error al actualizar el formato del estuche con ID: " + boxFormatId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listBoxFormats() {
        return ResponseEntity.ok(formatService.getBoxFormats());
    }

    @GetMapping("/id/{boxFormatId}")
    public BoxFormat listBoxFormat(@PathVariable("boxFormatId") Long boxFormatId) {
        return formatService.getBoxFormat(boxFormatId);
    }


    @DeleteMapping("/{boxFormatId}")
    public void deleteBoxFormat(@PathVariable("boxFormatId") Long boxFormatId) {
        formatService.deleteBoxFormat(boxFormatId);
    }

    @GetMapping("/boxNames/{boxNameId}/boxFormats")
    public List<BoxFormat> getBoxFormatsByBoxName(@PathVariable Long boxNameId) {
        return formatService.getBoxFormatsByBoxName(boxNameId);
    }
}
