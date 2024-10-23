package com.sys.bio.back.services.reception;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.dto.OperatorTotalBalesDTO;
import com.sys.bio.back.models.reception.Reception;
import com.sys.bio.back.repositories.reception.ReceptionRepository;
import com.sys.bio.back.repositories.user.ResponsibleRepository;
import com.sys.bio.back.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    @Autowired
    private ReceptionRepository receptionRepository;

    @Autowired
    private ResponsibleRepository responsibleRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    @Override
    public Reception addReception(Reception reception) {
        Date date = reception.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        reception.setFilterDate(localDate);
        return receptionRepository.save(reception);
    }
    @Override
    public Reception updateReception(Reception reception) {
        Date date = reception.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        reception.setFilterDate(localDate);
        return receptionRepository.save(reception);
    }

    @Override
    public Set<Reception> getReceptions() {
        return new LinkedHashSet<>(receptionRepository.findAll());
    }

    @Override
    public Reception getReception(Long receptionId) {
        return receptionRepository.findById(receptionId).get();
    }

    @Override
    public void deleteReception(Long receptionId) {
        Reception reception = new Reception();
        reception.setReceptionId(receptionId);
        receptionRepository.delete(reception);
    }


    @Override
    public List<Reception> searchByResponsibleName(String name) {
        if (name != null) {
            return receptionRepository.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Reception> getReceptionsByDate(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return receptionRepository.findByDate(date);
    }


    public List<Reception> getReceptionsByDateRange(Date startDate, Date endDate) {
        return receptionRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reception> findAll() {
        return (List<Reception>) receptionRepository.findAll();
    }




    /*
    @Override
    public Map<String, Double> getTotalHoursByResponsibleForCurrentMonth() {
        List<Reception> receptions = receptionRepository.findAll();
        Map<String, Double> totalHoursByResponsible = new HashMap<>();

        for (Reception reception : receptions) {
            Responsible responsible = reception.getResponsible();
            Double totalHours = reception.getTotalHours();
            totalHoursByResponsible.merge(responsible.getName(), totalHours, Double::sum);
        }
        return totalHoursByResponsible;
    }
     */

    public List<OperatorTotalBalesDTO> getBalesByResponsibleLastMonth(Date start, Date end) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

        // Convertir LocalDate a Date
        start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return receptionRepository.findBalesByResponsibleLastMonth(start, end);
    }
}
