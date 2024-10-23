package com.sys.bio.back.controllers.sized;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.criteria.SizedBoxCriteria;
import com.sys.bio.back.criteria.SizingCriteria;
import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.dto.SearchSizedBoxDTO;
import com.sys.bio.back.models.dto.SearchSizingDTO;
import com.sys.bio.back.models.sized.SizedBox;
import com.sys.bio.back.models.sized.Sizing;
import com.sys.bio.back.services.sized.SizedBoxCriteriaService;
import com.sys.bio.back.services.sized.SizingCriteriaService;
import com.sys.bio.back.services.sized.SizingService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sizings")
@CrossOrigin("*")
public class SizingController {

    @Autowired
    private SizingService sizingService;

    @Autowired
    private SizingCriteriaService criteriaService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Sizing> saveSizing(@RequestBody Sizing sizing) {
        return ResponseEntity.ok(sizingService.addSizing(sizing));
    }

    @PutMapping("/update/{sizingId}")
    public ResponseEntity<Sizing> updateSizing(@PathVariable("sizingId") Long sizingId,
                                                 @Valid @RequestBody Sizing sizing) {
        try {
            sizing.setSizingId(sizingId);
            Sizing updatedSizing = sizingService.updateSizing(sizing);
            return ResponseEntity.ok(updatedSizing);
        } catch (Exception e) {
            log.error("Error al actualizar el proceso de dimensionado con ID: " + sizingId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getSizings() {
        return ResponseEntity.ok(sizingService.getSizings());
    }

    @GetMapping("/id/{sizingId}")
    public Sizing getSizing(@PathVariable("sizingId") Long sizingId) {
        return sizingService.getSizing(sizingId);
    }

    @DeleteMapping("/{sizingId}")
    public void deleteSizing(@PathVariable("sizingId") Long sizingId) { sizingService.deleteSizing(sizingId); }


    @PostMapping("/lists")
    public ResponseEntity<List<Sizing>> list(@RequestBody SearchSizingDTO searchDTO) {
        SizingCriteria sizingCriteria = createCriteria(searchDTO);
        List<Sizing> list = criteriaService.findByCriteria(sizingCriteria);
        return new ResponseEntity<List<Sizing>>(list, HttpStatus.OK);
    }

    private SizingCriteria createCriteria(SearchSizingDTO dto) {
        SizingCriteria sizingCriteria = new SizingCriteria();
        if(dto!= null) {
            if(!StringUtils.isBlank(dto.getResponsible())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getResponsible());
                sizingCriteria.setResponsible(filter);
            }
            if(dto.getStartTotalWeight() != null || dto.getEndTotalWeight() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartTotalWeight() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartTotalWeight());
                    sizingCriteria.setTotalWeight(filter);
                }
                if(dto.getEndTotalWeight() != null) {
                    filter.setLessThanOrEqual(dto.getEndTotalWeight());
                    sizingCriteria.setTotalWeight(filter);
                }
            }
            if(dto.getStartTotalAmount() != null || dto.getEndTotalAmount() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartTotalAmount() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartTotalAmount());
                    sizingCriteria.setTotalAmount(filter);
                }
                if(dto.getEndTotalAmount() != null) {
                    filter.setLessThanOrEqual(dto.getEndTotalAmount());
                    sizingCriteria.setTotalAmount(filter);
                }
            }
            if(dto.getStartTotalHours() != null || dto.getEndTotalHours() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartTotalHours() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartTotalHours());
                    sizingCriteria.setTotalHours(filter);
                }
                if(dto.getEndTotalHours() != null) {
                    filter.setLessThanOrEqual(dto.getEndTotalHours());
                    sizingCriteria.setTotalHours(filter);
                }
            }
            if(dto.getStartDate() != null || dto.getEndDate() != null) {
                LocalDateFilter filter = new LocalDateFilter();
                if(dto.getStartDate() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartDate());
                    sizingCriteria.setFilterDate(filter);
                }
                if(dto.getEndDate() != null) {
                    filter.setLessThanOrEqual(dto.getEndDate());
                    sizingCriteria.setFilterDate(filter);
                }
            }
        }
        return sizingCriteria;
    }

}
