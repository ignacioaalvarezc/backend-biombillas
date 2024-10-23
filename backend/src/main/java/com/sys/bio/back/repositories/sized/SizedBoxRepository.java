package com.sys.bio.back.repositories.sized;

import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.sized.SizedBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SizedBoxRepository extends JpaRepository<SizedBox, Long>,
        JpaSpecificationExecutor<SizedBox> {

    List<SizedBox> findByDateBetween(Date startDate, Date endDate);

    List<SizedBox> findByResponsibleNameContainingIgnoreCase(String name);

    List<SizedBox> findBySizingSizingId(Long sizingId);


}
