package com.sys.bio.back.services.cutting;

import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.dto.ResponsibleHoursSumDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CuttingService {

    Cutting addCutting(Cutting cutting);
    Cutting updateCutting(Cutting cutting);
    Set<Cutting> getCuttings();
    Cutting getCutting(Long cuttingId);
    void deleteCutting(Long cuttingId);
    List<Cutting> getCuttingsByDateRange(Date startDate, Date endDate);
    List<Cutting> searchByResponsibleName(String name);
    List<ResponsibleHoursSumDTO> getTotalHoursByResponsibleForLastMonth();

}
