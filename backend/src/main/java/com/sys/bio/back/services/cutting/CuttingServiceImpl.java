package com.sys.bio.back.services.cutting;

import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.dto.ResponsibleHoursSumDTO;
import com.sys.bio.back.models.user.Responsible;
import com.sys.bio.back.repositories.cutting.CuttingRepository;
import com.sys.bio.back.repositories.user.ResponsibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CuttingServiceImpl implements CuttingService {

    @Autowired
    private CuttingRepository cuttingRepository;

    @Autowired
    private ResponsibleRepository responsibleRepository;

    @Override
    public Cutting addCutting(Cutting cutting) {
        return cuttingRepository.save(cutting);
    }

    @Override
    public Cutting updateCutting(Cutting cutting) {
        return cuttingRepository.save(cutting);
    }

    @Override
    public Set<Cutting> getCuttings() {
        return new LinkedHashSet<>(cuttingRepository.findAll());
    }

    @Override
    public Cutting getCutting(Long cuttingId) {
        return cuttingRepository.findById(cuttingId).get();
    }

    @Override
    public void deleteCutting(Long cuttingId) {
        Cutting cutting = new Cutting();
        cutting.setCuttingId(cuttingId);
        cuttingRepository.delete(cutting);
    }

    @Override
    public List<Cutting> getCuttingsByDateRange(Date startDate, Date endDate) {
        return cuttingRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<Cutting> searchByResponsibleName(String name) {
        if (name != null) {
            return cuttingRepository.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ResponsibleHoursSumDTO> getTotalHoursByResponsibleForLastMonth() {
        List<Object[]> results = cuttingRepository.findTotalHoursByResponsibleLastMonth();
        return results.stream()
                .map(result -> {
                    Long responsibleId = (Long) result[0];
                    Integer totalHours = ((Number) result[1]).intValue();
                    Responsible responsible = responsibleRepository.findById(responsibleId).orElse(null);
                    assert responsible != null;
                    return new ResponsibleHoursSumDTO(responsible.getName(), totalHours);
                })
                .collect(Collectors.toList());
    }



}
