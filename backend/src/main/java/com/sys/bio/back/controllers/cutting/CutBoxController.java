package com.sys.bio.back.controllers.cutting;

import com.lowagie.text.DocumentException;
import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.criteria.CutBoxCriteria;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.dto.SearchCutBoxDTO;
import com.sys.bio.back.services.cutting.CutBoxCriteriaService;
import com.sys.bio.back.services.cutting.CutBoxService;
import com.sys.bio.back.services.cutting.CutTypeService;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/cutBoxes")
@CrossOrigin("*")
public class CutBoxController {

    @Autowired
    private CutBoxService boxService;

    @Autowired
    private CutTypeService typeService;

    @Autowired
    private CutBoxCriteriaService criteriaService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<CutBox> saveCutBox(@RequestBody CutBox cutBox) {
        return ResponseEntity.ok(boxService.addCutBox(cutBox));
    }

    @PutMapping("/update/{cutBoxId}")
    public ResponseEntity<CutBox> updateCutBox(@PathVariable("cutBoxId") Long cutBoxId,
                                                     @Valid @RequestBody CutBox cutBox) {
        try {
            cutBox.setCutBoxId(cutBoxId);
            CutBox updatedCutBox = boxService.updateCutBox(cutBox);
            return ResponseEntity.ok(updatedCutBox);
        } catch (Exception e) {
            log.error("Error al actualizar la caja de corte con ID: " + cutBoxId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listCutBoxes() {
        return ResponseEntity.ok(boxService.getCutBoxes());
    }

    @GetMapping("/id/{cutBoxId}")
    public CutBox listCutBox(@PathVariable("cutBoxId") Long cutBoxId) {
        return boxService.getCutBox(cutBoxId);
    }


    @DeleteMapping("/{cutBoxId}")
    public void deleteCutBox(@PathVariable("cutBoxId") Long cutBoxId) {
        boxService.deleteCutBox(cutBoxId);
    }


    @GetMapping("/date-filter")
    public ResponseEntity<List<CutBox>> getFilteredCutBoxes(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<CutBox> filteredCutBoxes = boxService.getCutBoxesByDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredCutBoxes, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<CutBox>> searchByResponsibleName(
            @RequestParam(value = "name", required = false) String name) {
        List<CutBox> cutBoxes = boxService.searchByResponsibleName(name);
        return new ResponseEntity<>(cutBoxes, HttpStatus.OK);
    }

    @GetMapping("/searchCutType")
    public ResponseEntity<List<CutBox>> searchByCutTypeName(
            @RequestParam(value = "name", required = false) String name) {
        List<CutBox> cutBoxes = boxService.searchByCutTypeName(name);
        return new ResponseEntity<>(cutBoxes, HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAllCutBoxes(@RequestBody List<CutBox> cutBoxes) {
        try {
            boxService.saveAll(cutBoxes);
            return ResponseEntity.ok("Cut boxes has saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving cut boxes: " + e.getMessage());
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + ex.getMessage());
    }


    @GetMapping("/byCutting/{cuttingId}")
    public ResponseEntity<List<CutBox>> getCutBoxesByCuttingId(@PathVariable Long cuttingId) {
        List<CutBox> cutBoxes = boxService.getCutBoxesByCuttingId(cuttingId);
        return new ResponseEntity<>(cutBoxes, HttpStatus.OK);
    }

    @PostMapping("/exportPdf")
    public void exportCutBoxListToPdf(@RequestBody List<CutBox> filteredCutBoxes, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=Cortes_" + currentDate + ".pdf";
        response.setHeader(header, value);

        CutBoxExporterPDF exporter = new CutBoxExporterPDF(filteredCutBoxes);
        exporter.export(response);
    }

    @PostMapping("/exportExcel")
    public void exportCutBoxListToExcel(@RequestBody List<CutBox> filteredCutBoxes, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=Cortes_" + currentDate + ".xlsx";
        response.setHeader(header, value);

        CutBoxExporterExcel exporter = new CutBoxExporterExcel(filteredCutBoxes);
        exporter.export(response);
    }

    @PostMapping("/lists")
    public ResponseEntity<List<CutBox>> list(@RequestBody SearchCutBoxDTO searchDTO) {
        CutBoxCriteria cutBoxCriteria = createCriteria(searchDTO);
        List<CutBox> list = criteriaService.findByCriteria(cutBoxCriteria);
        return new ResponseEntity<List<CutBox>>(list, HttpStatus.OK);
    }

    private CutBoxCriteria createCriteria(SearchCutBoxDTO dto) {
        CutBoxCriteria cutBoxCriteria = new CutBoxCriteria();
        if(dto!= null) {
            if(!StringUtils.isBlank(dto.getResponsible())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getResponsible());
                cutBoxCriteria.setResponsible(filter);
            }
            if(!StringUtils.isBlank(dto.getCutType())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getResponsible());
                cutBoxCriteria.setCutType(filter);
            }
            if(dto.getStartWeight() != null || dto.getEndWeight() != null) {
                DoubleFilter filter = new DoubleFilter();
                if(dto.getStartWeight() != null) {
                    filter.setGreaterThanOrEqual(Double.valueOf(dto.getStartWeight()));
                    cutBoxCriteria.setWeight(filter);
                }
                if(dto.getEndWeight() != null) {
                    filter.setLessThanOrEqual(Double.valueOf(dto.getEndWeight()));
                    cutBoxCriteria.setWeight(filter);
                }
            }
            if(dto.getStartAmount() != null || dto.getEndAmount() != null) {
                IntegerFilter filter = new IntegerFilter();
                if(dto.getStartAmount() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartAmount());
                    cutBoxCriteria.setAmount(filter);
                }
                if(dto.getEndAmount() != null) {
                    filter.setLessThanOrEqual(dto.getEndAmount());
                    cutBoxCriteria.setAmount(filter);
                }
            }
            if(dto.getStartDate() != null || dto.getEndDate() != null) {
                LocalDateFilter filter = new LocalDateFilter();
                if(dto.getStartDate() != null) {
                    filter.setGreaterThanOrEqual(dto.getStartDate());
                    cutBoxCriteria.setFilterDate(filter);
                }
                if(dto.getEndDate() != null) {
                    filter.setLessThanOrEqual(dto.getEndDate());
                    cutBoxCriteria.setFilterDate(filter);
                }
            }
        }
        return cutBoxCriteria;
    }

    @GetMapping("/totalAmountForMiniCuts")
    public Integer getTotalAmountForMiniCuts() {
        Integer total = boxService.getTotalAmountForMiniCuts();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }
    @GetMapping("/totalAmountForStandardCuts")
    public Integer getTotalAmountForStandardCuts() {
        Integer total = boxService.getTotalAmountForStandardCuts();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }
    @GetMapping("/totalAmountForBiggyCuts")
    public Integer getTotalAmountForBiggyCuts() {
        Integer total = boxService.getTotalAmountForBiggyCuts();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }



    @GetMapping("/totalAmountForMiniCutsByCurrentMonth")
    public Integer getTotalAmountForMiniCutsByCurrentMonth() {
        Integer total = boxService.getTotalAmountForMiniCutsByCurrentMonth();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }
    @GetMapping("/totalAmountForStandardCutsByCurrentMonth")
    public Integer getTotalAmountForStandardCutsByCurrentMonth() {
        Integer total = boxService.getTotalAmountForStandardCutsByCurrentMonth();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }
    @GetMapping("/totalAmountForBiggyCutsByCurrentMonth")
    public Integer getTotalAmountForBiggyCutsByCurrentMonth() {
        Integer total = boxService.getTotalAmountForBiggyCutsByCurrentMonth();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }



    @GetMapping("totalAmountForMiniCutsByPreviousMonth")
    public Integer getTotalAmountForMiniCutsByPreviousMonth() {
        Integer total = boxService.getTotalAmountForMiniCutsByPreviousMonth();
        System.out.println("Valor total obtenido desde el servicio para el mes anterior: " + total);
        return total;
    }
    @GetMapping("totalAmountForStandardCutsByPreviousMonth")
    public Integer getTotalAmountForStandardCutsByPreviousMonth() {
        Integer total = boxService.getTotalAmountForStandardCutsByPreviousMonth();
        System.out.println("Valor total obtenido desde el servicio para el mes anterior: " + total);
        return total;
    }
    @GetMapping("totalAmountForBiggyCutsByPreviousMonth")
    public Integer getTotalAmountForBiggyCutsByPreviousMonth() {
        Integer total = boxService.getTotalAmountForBiggyCutsByPreviousMonth();
        System.out.println("Valor total obtenido desde el servicio para el mes anterior: " + total);
        return total;
    }


    @GetMapping("/totalWeightForMiniCutsByCurrentMonth")
    public Integer getTotalWeightForMiniCutsByCurrentMonth() {
        Integer total = boxService.getTotalWeightForMiniCutsByCurrentMonth();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }
    @GetMapping("/totalWeightForStandardCutsByCurrentMonth")
    public Integer getTotalWeightForStandardCutsByCurrentMonth() {
        Integer total = boxService.getTotalWeightForStandardCutsByCurrentMonth();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }
    @GetMapping("/totalWeightForBiggyCutsByCurrentMonth")
    public Integer getTotalWeightForBiggyCutsByCurrentMonth() {
        Integer total = boxService.getTotalWeightForBiggyCutsByCurrentMonth();
        System.out.println("Valor total obtenido desde el servicio: " + total);
        return total;
    }

    @GetMapping("totalWeightForMiniCutsByPreviousMonth")
    public Integer getTotalWeightForMiniCutsByPreviousMonth() {
        Integer total = boxService.getTotalWeightForMiniCutsByPreviousMonth();
        System.out.println("Valor total obtenido desde el servicio para el mes anterior: " + total);
        return total;
    }
    @GetMapping("totalWeightForStandardCutsByPreviousMonth")
    public Integer getTotalWeightForStandardCutsByPreviousMonth() {
        Integer total = boxService.getTotalWeightForStandardCutsByPreviousMonth();
        System.out.println("Valor total obtenido desde el servicio para el mes anterior: " + total);
        return total;
    }
    @GetMapping("totalWeightForBiggyCutsByPreviousMonth")
    public Integer getTotalWeightForBiggyCutsByPreviousMonth() {
        Integer total = boxService.getTotalWeightForBiggyCutsByPreviousMonth();
        System.out.println("Valor total obtenido desde el servicio para el mes anterior: " + total);
        return total;
    }











    @GetMapping("/totalAmountByCutType/{name}")
    public Integer getTotalAmountByCutType(@PathVariable String name) {
        return boxService.getTotalAmountByCutType(name);
    }

}
