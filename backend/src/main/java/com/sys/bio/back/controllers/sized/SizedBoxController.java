package com.sys.bio.back.controllers.sized;

import com.lowagie.text.DocumentException;
import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.criteria.CutBoxCriteria;
import com.sys.bio.back.criteria.SizedBoxCriteria;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.dto.SearchCutBoxDTO;
import com.sys.bio.back.models.dto.SearchSizedBoxDTO;
import com.sys.bio.back.models.sized.SizedBox;
import com.sys.bio.back.services.cutting.CutBoxCriteriaService;
import com.sys.bio.back.services.cutting.CutBoxService;
import com.sys.bio.back.services.cutting.CutTypeService;
import com.sys.bio.back.services.sized.SizedBoxCriteriaService;
import com.sys.bio.back.services.sized.SizedBoxService;
import com.sys.bio.back.services.sized.StrawTypeService;
import com.sys.bio.back.utils.report.excel.CutBoxExporterExcel;
import com.sys.bio.back.utils.report.pdf.CutBoxExporterPDF;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sizedBoxes")
@CrossOrigin("*")
public class SizedBoxController {


    @Autowired
    private SizedBoxService boxService;

    @Autowired
    private StrawTypeService typeService;

    @Autowired
    private SizedBoxCriteriaService criteriaService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<SizedBox> saveSizedBox(@RequestBody SizedBox sizedBox) {
        return ResponseEntity.ok(boxService.addSizedBox(sizedBox));
    }

    @PutMapping("/update/{sizedBoxId}")
    public ResponseEntity<SizedBox> updateSizedBox(@PathVariable("sizedBoxId") Long sizedBoxId,
                                               @Valid @RequestBody SizedBox sizedBox) {
        try {
            sizedBox.setSizedBoxId(sizedBoxId);
            SizedBox updatedSizedBox = boxService.updateSizedBox(sizedBox);
            return ResponseEntity.ok(updatedSizedBox);
        } catch (Exception e) {
            log.error("Error al actualizar la caja de corte con ID: " + sizedBoxId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listSizedBoxes() {
        return ResponseEntity.ok(boxService.getSizedBoxes());
    }

    @GetMapping("/id/{sizedBoxId}")
    public SizedBox listSizedBox(@PathVariable("sizedBoxId") Long sizedBoxId) {
        return boxService.getSizedBox(sizedBoxId);
    }


    @DeleteMapping("/{sizedBoxId}")
    public void deleteSizedBox(@PathVariable("sizedBoxId") Long sizedBoxId) {
        boxService.deleteSizedBox(sizedBoxId);
    }


    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAllSizedBoxes(@RequestBody List<SizedBox> sizedBoxes) {
        try {
            boxService.saveAll(sizedBoxes);
            return ResponseEntity.ok("Sized boxes has saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving sized boxes: " + e.getMessage());
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + ex.getMessage());
    }

    @GetMapping("/bySizing/{sizingId}")
    public ResponseEntity<List<SizedBox>> getSizedBoxesBySizingId(@PathVariable Long sizingId) {
        List<SizedBox> sizedBoxes = boxService.getSizedBoxesBySizingId(sizingId);
        return new ResponseEntity<>(sizedBoxes, HttpStatus.OK);
    }

    @PostMapping("/lists")
    public ResponseEntity<List<SizedBox>> list(@RequestBody SearchSizedBoxDTO searchDTO) {
        SizedBoxCriteria sizedBoxCriteria = createCriteria(searchDTO);
        List<SizedBox> list = criteriaService.findByCriteria(sizedBoxCriteria);
        return new ResponseEntity<List<SizedBox>>(list, HttpStatus.OK);
    }

    private SizedBoxCriteria createCriteria(SearchSizedBoxDTO dto) {
        SizedBoxCriteria sizedBoxCriteria = new SizedBoxCriteria();
        if(dto!= null) {
            if(!StringUtils.isBlank(dto.getResponsible())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getResponsible());
                sizedBoxCriteria.setResponsible(filter);
            }
            if(!StringUtils.isBlank(dto.getStrawType())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getStrawType());
                sizedBoxCriteria.setStrawType(filter);
            }
            if(dto.getStartWeight() != null || dto.getEndWeight() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartWeight() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartWeight());
                    sizedBoxCriteria.setWeight(filter);
                }
                if(dto.getEndWeight() != null) {
                    filter.setLessThanOrEqual(dto.getEndWeight());
                    sizedBoxCriteria.setWeight(filter);
                }
            }
            if(dto.getStartAmount() != null || dto.getEndAmount() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartAmount() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartAmount());
                    sizedBoxCriteria.setAmount(filter);
                }
                if(dto.getEndAmount() != null) {
                    filter.setLessThanOrEqual(dto.getEndAmount());
                    sizedBoxCriteria.setAmount(filter);
                }
            }
            if(dto.getStartDate() != null || dto.getEndDate() != null) {
                LocalDateFilter filter = new LocalDateFilter();
                if(dto.getStartDate() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartDate());
                    sizedBoxCriteria.setFilterDate(filter);
                }
                if(dto.getEndDate() != null) {
                    filter.setLessThanOrEqual(dto.getEndDate());
                    sizedBoxCriteria.setFilterDate(filter);
                }
            }
        }
        return sizedBoxCriteria;
    }


}
