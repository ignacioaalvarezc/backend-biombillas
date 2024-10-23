package com.sys.bio.back.repositories.reception;

import com.sys.bio.back.models.dto.OperatorTotalBalesDTO;
import com.sys.bio.back.models.reception.Reception;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReceptionRepository extends JpaRepository<Reception, Long>,
        JpaSpecificationExecutor<Reception> {

    @Query("SELECT r.receptionId FROM Reception r")
    List<Long> findAllIds();

    @Query("SELECT r FROM Reception r WHERE DATE(r.date) = DATE(:date)")
    List<Reception> findByDate(@Param("date") Date date);

    List<Reception> findByDateBetween(Date startDate, Date endDate);
    
    List<Reception> findByResponsibleNameContainingIgnoreCase(String name);


    @Query("SELECT new com.sys.bio.back.models.dto.OperatorTotalBalesDTO(r.responsible.name, SUM(r.acceptedBales), SUM(r.rejectedBales)) " +
            "FROM Reception r " +
            "WHERE r.date BETWEEN :startDate AND :endDate " +
            "GROUP BY r.responsible.name")
    List<OperatorTotalBalesDTO> findBalesByResponsibleLastMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
