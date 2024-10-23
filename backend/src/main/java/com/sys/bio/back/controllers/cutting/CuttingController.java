package com.sys.bio.back.controllers.cutting;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.criteria.CutBoxCriteria;
import com.sys.bio.back.criteria.CuttingCriteria;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.dto.ResponsibleHoursSumDTO;
import com.sys.bio.back.models.dto.SearchCutBoxDTO;
import com.sys.bio.back.models.dto.SearchCuttingDTO;
import com.sys.bio.back.services.cutting.CutBoxCriteriaService;
import com.sys.bio.back.services.cutting.CuttingCriteriaService;
import com.sys.bio.back.services.cutting.CuttingService;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
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
import java.util.Map;

@RestController
@RequestMapping("/cuttings")
@CrossOrigin("*")
public class CuttingController {

    @Autowired
    private CuttingService cuttingService;

    @Autowired
    private CuttingCriteriaService criteriaService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/")
    public ResponseEntity<Cutting> saveCutting(@RequestBody Cutting cutting) {
        return ResponseEntity.ok(cuttingService.addCutting(cutting));
    }

    @GetMapping("/hours-summary")
    public List<ResponsibleHoursSumDTO> getTotalHoursByResponsibleLastMonth() {
        return cuttingService.getTotalHoursByResponsibleForLastMonth();
    }

    @PutMapping("/update/{cuttingId}")
    public ResponseEntity<Cutting> updateCutting(@PathVariable("cuttingId") Long cuttingId,
                                                 @Valid @RequestBody Cutting cutting) {
        try {
            cutting.setCuttingId(cuttingId);
            Cutting updatedCutting = cuttingService.updateCutting(cutting);
            return ResponseEntity.ok(updatedCutting);
        } catch (Exception e) {
            log.error("Error al actualizar el proceso de corte con ID: " + cuttingId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getCuttings() {
        return ResponseEntity.ok(cuttingService.getCuttings());
    }

    @GetMapping("/id/{cuttingId}")
    public Cutting getCutting(@PathVariable("cuttingId") Long cuttingId) {
        return cuttingService.getCutting(cuttingId);
    }

    @DeleteMapping("/{cuttingId}")
    public void deleteCutting(@PathVariable("cuttingId") Long cuttingId) {
        cuttingService.deleteCutting(cuttingId);
    }

    @GetMapping("/date-filter")
    public ResponseEntity<List<Cutting>> getFilteredCuttings(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Cutting> filteredCuttings = cuttingService.getCuttingsByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredCuttings, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Cutting>> searchByResponsibleName(
            @RequestParam(value = "name", required = false) String name) {
        List<Cutting> cuttings = cuttingService.searchByResponsibleName(name);
        return new ResponseEntity<>(cuttings, HttpStatus.OK);
    }

    @PostMapping("/lists")
    public ResponseEntity<List<Cutting>> list(@RequestBody SearchCuttingDTO searchDTO) {
        CuttingCriteria cuttingCriteria = createCriteria(searchDTO);
        List<Cutting> list = criteriaService.findByCriteria(cuttingCriteria);
        return new ResponseEntity<List<Cutting>>(list, HttpStatus.OK);
    }

    private CuttingCriteria createCriteria(SearchCuttingDTO dto) {
        CuttingCriteria cuttingCriteria = new CuttingCriteria();
        if(dto!= null) {
            if(!StringUtils.isBlank(dto.getResponsible())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getResponsible());
                cuttingCriteria.setResponsible(filter);
            }

            if(dto.getStartTotalWeight() != null || dto.getEndTotalWeight() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartTotalWeight() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartTotalWeight());
                    cuttingCriteria.setTotalWeight(filter);
                }
                if(dto.getEndTotalWeight() != null) {
                    filter.setLessThanOrEqual(dto.getEndTotalWeight());
                    cuttingCriteria.setTotalWeight(filter);
                }
            }
            if(dto.getStartTotalAmount() != null || dto.getEndTotalAmount() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartTotalAmount() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartTotalAmount());
                    cuttingCriteria.setTotalAmount(filter);
                }
                if(dto.getEndTotalAmount() != null) {
                    filter.setLessThanOrEqual(dto.getEndTotalAmount());
                    cuttingCriteria.setTotalAmount(filter);
                }
            }
            if(dto.getStartTotalHours() != null || dto.getEndTotalHours() != null) {
                DoubleFilter filter = new DoubleFilter();
                if(dto.getStartTotalHours() != null) {
                    filter.setGreaterThanOrEqual(Double.valueOf(dto.getStartTotalHours()));
                    cuttingCriteria.setTotalHours(filter);
                }
                if(dto.getEndTotalHours() != null) {
                    filter.setLessThanOrEqual(Double.valueOf(dto.getEndTotalHours()));
                    cuttingCriteria.setTotalHours(filter);
                }
            }
            if(dto.getStartDate() != null || dto.getEndDate() != null) {
                LocalDateFilter filter = new LocalDateFilter();
                if(dto.getStartDate() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartDate());
                    cuttingCriteria.setFilterDate(filter);
                }
                if(dto.getEndDate() != null) {
                    filter.setLessThanOrEqual(dto.getEndDate());
                    cuttingCriteria.setFilterDate(filter);
                }
            }
        }
        return cuttingCriteria;
    }


}
