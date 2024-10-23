package com.sys.bio.back.repositories.sanitized;

import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.sanitized.SanitizedBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SanitizedBoxRepository extends JpaRepository<SanitizedBox, Long> {
    List<SanitizedBox> findByDateBetween(Date startDate, Date endDate);

    List<SanitizedBox> findByResponsibleNameContainingIgnoreCase(String name);

    List<SanitizedBox> findByState(String state);

    List<SanitizedBox> findBySanitizedSanitizedId(Long sanitizedId);

}
