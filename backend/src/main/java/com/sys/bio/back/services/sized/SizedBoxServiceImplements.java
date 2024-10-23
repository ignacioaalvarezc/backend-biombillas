package com.sys.bio.back.services.sized;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.sized.SizedBox;
import com.sys.bio.back.repositories.sized.SizedBoxRepository;
import com.sys.bio.back.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;

@Service
public class SizedBoxServiceImplements implements SizedBoxService {

    @Autowired
    private SizedBoxRepository boxRepo;

    @Autowired
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public SizedBox addSizedBox(SizedBox sizedBox) {
        Date date = sizedBox.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        sizedBox.setFilterDate(localDate);
        return boxRepo.save(sizedBox);
    }

    @Override
    public SizedBox updateSizedBox(SizedBox sizedBox) {
        Date date = sizedBox.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        sizedBox.setFilterDate(localDate);
        return boxRepo.save(sizedBox);
    }

    @Override
    public Set<SizedBox> getSizedBoxes() {
        return new LinkedHashSet<>(boxRepo.findAll());
    }

    @Override
    public SizedBox getSizedBox(Long sizedBoxId) {
        return boxRepo.findById(sizedBoxId).get();
    }

    @Override
    public void deleteSizedBox(Long sizedBoxId) {
        SizedBox sizedBox = new SizedBox();
        sizedBox.setSizedBoxId(sizedBoxId);
        boxRepo.delete(sizedBox);
    }

    @Override
    public List<SizedBox> searchByResponsibleName(String name) {
        if (name != null) {
            return boxRepo.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }


    public List<SizedBox> getSizedBoxesByDateRange(Date startDate, Date endDate) {
        return boxRepo.findByDateBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SizedBox> findAll() {
        return (List<SizedBox>) boxRepo.findAll();
    }

    @Override
    public void saveAll(List<SizedBox> sizedBoxes) {
        boxRepo.saveAll(sizedBoxes);
    }


    @Override
    public List<SizedBox> getSizedBoxesBySizingId(Long sizingId) {
        return boxRepo.findBySizingSizingId(sizingId);
    }

}
