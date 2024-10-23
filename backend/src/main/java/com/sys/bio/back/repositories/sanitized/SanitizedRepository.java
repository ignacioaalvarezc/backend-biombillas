package com.sys.bio.back.repositories.sanitized;

import com.sys.bio.back.models.sanitized.Sanitized;
import com.sys.bio.back.models.sanitized.SanitizedBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SanitizedRepository extends JpaRepository<Sanitized, Long> {

    List<Sanitized> findByDateBetween(Date startDate, Date endDate);

    List<Sanitized> findByResponsibleNameContainingIgnoreCase(String name);


}
