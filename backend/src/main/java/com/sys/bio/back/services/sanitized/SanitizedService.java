package com.sys.bio.back.services.sanitized;

import com.sys.bio.back.models.sanitized.Sanitized;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SanitizedService {

    Sanitized addSanitized(Sanitized sanitized);
    Sanitized updateSanitized(Sanitized sanitized);
    Set<Sanitized> getSanitizeds();
    Sanitized getSanitized(Long sanitizedId);
    void deleteSanitized(Long sanitizedId);
    List<Sanitized> getSanitizedsByDateRange(Date startDate, Date endDate);
    List<Sanitized> searchByResponsibleName(String name);
}
