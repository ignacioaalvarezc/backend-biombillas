package com.sys.bio.back.repositories.cutting;

import com.sys.bio.back.models.cutting.Cutting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CuttingRepository extends JpaRepository<Cutting, Long>,
        JpaSpecificationExecutor<Cutting> {

    @Query("SELECT c.cuttingId FROM Cutting c")
    List<Long> findAllIds();

    List<Cutting> findByDateBetween(Date startDate, Date endDate);

    List<Cutting> findByResponsibleNameContainingIgnoreCase(String name);

    @Query("SELECT c.responsible.responsibleId, SUM(c.totalHours)" +
            "FROM Cutting c " +
            "WHERE FUNCTION('YEAR', c.date) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', c.date) = FUNCTION('MONTH', CURRENT_DATE) - 1 " +
            "GROUP BY c.responsible.responsibleId")
    List<Object[]> findTotalHoursByResponsibleLastMonth();

}
