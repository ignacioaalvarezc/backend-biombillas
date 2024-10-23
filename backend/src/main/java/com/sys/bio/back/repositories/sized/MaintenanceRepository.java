package com.sys.bio.back.repositories.sized;

import com.sys.bio.back.models.sized.Maintenance;
import com.sys.bio.back.models.sized.SizedBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByDateAlertBetween(Date startDate, Date endDate);

}
