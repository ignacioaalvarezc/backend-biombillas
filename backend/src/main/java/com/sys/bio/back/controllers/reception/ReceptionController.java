package com.sys.bio.back.controllers.reception;

import com.lowagie.text.DocumentException;
import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.criteria.ReceptionCriteria;
import com.sys.bio.back.models.dto.OperatorTotalBalesDTO;
import com.sys.bio.back.models.dto.SearchReceptionDTO;
import com.sys.bio.back.models.reception.Reception;
import com.sys.bio.back.services.reception.ReceptionCriteriaService;
import com.sys.bio.back.services.reception.ReceptionService;
import com.sys.bio.back.utils.report.excel.ReceptionExporterExcel;
import com.sys.bio.back.utils.report.pdf.ReceptionExporterPDF;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/receptions")
@CrossOrigin("*")
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    @Autowired
    private ReceptionCriteriaService criteriaService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Reception> saveReception(@RequestBody Reception reception) {
        return ResponseEntity.ok(receptionService.addReception(reception));
    }

    @PutMapping("/update/{receptionId}")
    public ResponseEntity<Reception> updateReception(@PathVariable("receptionId") Long receptionId,
                                                     @Valid @RequestBody Reception reception) {
        try {
            reception.setReceptionId(receptionId);
            Reception updatedReception = receptionService.updateReception(reception);
            return ResponseEntity.ok(updatedReception);
        } catch (Exception e) {
            log.error("Error al actualizar la recepción con ID: " + receptionId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listReceptions() {
        return ResponseEntity.ok(receptionService.getReceptions());
    }

    @GetMapping("/id/{receptionId}")
    public Reception listReception(@PathVariable("receptionId") Long receptionId) {
        return receptionService.getReception(receptionId);
    }


    @DeleteMapping("/{receptionId}")
    public void deleteReception(@PathVariable("receptionId") Long receptionId) {
        receptionService.deleteReception(receptionId);
    }

    @GetMapping("/today")
    public List<Reception> getTodayReceptions() {
        LocalDate today = LocalDate.now();
        return receptionService.getReceptionsByDate(today);
    }

    @GetMapping("/date-filter")
    public ResponseEntity<List<Reception>> getFilteredReceptions(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Reception> filteredReceptions = receptionService.getReceptionsByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredReceptions, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Reception>> searchByResponsibleName(
        @RequestParam(value = "name", required = false) String name) {
            List<Reception> receptions = receptionService.searchByResponsibleName(name);
            return new ResponseEntity<>(receptions, HttpStatus.OK);
    }

    @PostMapping("/exportPdf")
    public void exportReceptionListToPdf(@RequestBody List<Reception> filteredReceptions, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=Recepciones_" + currentDate + ".pdf";
        response.setHeader(header, value);

        ReceptionExporterPDF exporter = new ReceptionExporterPDF(filteredReceptions);
        exporter.export(response);
    }

    @PostMapping("/exportExcel")
    public void exportReceptionListToExcel(@RequestBody List<Reception> filteredReceptions, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=Recepciones_" + currentDate + ".xlsx";
        response.setHeader(header, value);

        ReceptionExporterExcel exporter = new ReceptionExporterExcel(filteredReceptions);
        exporter.export(response);
    }

    @PostMapping("/lists")
    public ResponseEntity<List<Reception>> list(@RequestBody SearchReceptionDTO searchDTO) {
        ReceptionCriteria receptionCriteria = createCriteria(searchDTO);
        List<Reception> list = criteriaService.findByCriteria(receptionCriteria);
        return new ResponseEntity<List<Reception>>(list, HttpStatus.OK);
    }

    private ReceptionCriteria createCriteria(SearchReceptionDTO dto) {
        ReceptionCriteria receptionCriteria = new ReceptionCriteria();
        if(dto!= null) {
            if(!StringUtils.isBlank(dto.getResponsible())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getResponsible());
                receptionCriteria.setResponsible(filter);
            }
            if(dto.getStartAcceptedBales() != null || dto.getEndAcceptedBales() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartAcceptedBales() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartAcceptedBales());
                    receptionCriteria.setAcceptedBales(filter);
                }
                if(dto.getEndAcceptedBales() != null) {
                    filter.setLessThanOrEqual(dto.getEndAcceptedBales());
                    receptionCriteria.setAcceptedBales(filter);
                }
            }
            if(dto.getStartRejectedBales() != null || dto.getEndRejectedBales() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartRejectedBales() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartRejectedBales());
                    receptionCriteria.setRejectedBales(filter);
                }
                if(dto.getEndRejectedBales() != null) {
                    filter.setLessThanOrEqual(dto.getEndRejectedBales());
                    receptionCriteria.setRejectedBales(filter);
                }
            }
            if(dto.getStartDate() != null || dto.getEndDate() != null) {
                LocalDateFilter filter = new LocalDateFilter();
                if(dto.getStartDate() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartDate());
                    receptionCriteria.setFilterDate(filter);
                }
                if(dto.getEndDate() != null) {
                    filter.setLessThanOrEqual(dto.getEndDate());
                    receptionCriteria.setFilterDate(filter);
                }
            }
        }
        return receptionCriteria;
    }


    @GetMapping("/monthly")
    public ResponseEntity<List<Reception>> getReceptionsByMonth() {
        // GET THE FIRST AND LAST DAY OF CURRENT MONTH
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        Date endDate = cal.getTime();

        List<Reception> receptions = receptionService.getReceptionsByDateRange(startDate, endDate);
        return ResponseEntity.ok(receptions);
    }
/*
    @GetMapping("/totalHoursForMonth")
    public ResponseEntity<Map<String, Double>> getReceptionsAndTotalHoursByResponsibleForCurrentMonth() {
        Map<String, Double> totalHoursByResponsible = receptionService.getTotalHoursByResponsibleForCurrentMonth();
        return ResponseEntity.ok(totalHoursByResponsible);
    }
 */


    @GetMapping("/receptions-summary")
    public ResponseEntity<List<OperatorTotalBalesDTO>> getBalesSummary() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

// Convertir LocalDate a Date
        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

// Llamar al servicio con las fechas convertidas
        List<OperatorTotalBalesDTO> balesSummary = receptionService.getBalesByResponsibleLastMonth(start, end);
        return ResponseEntity.ok(balesSummary);
    }
}
