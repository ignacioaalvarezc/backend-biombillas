package com.sys.bio.back.services.cutting;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.repositories.cutting.CutBoxRepository;
import com.sys.bio.back.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.*;

@Service
public class CutBoxServiceImplements implements CutBoxService {

    @Autowired
    private CutBoxRepository boxRepo;

    @Autowired
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public CutBox addCutBox(CutBox cutBox) {
        Date date = cutBox.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        cutBox.setFilterDate(localDate);
        return boxRepo.save(cutBox);
    }

    @Override
    public CutBox updateCutBox(CutBox cutBox) {
        Date date = cutBox.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        cutBox.setFilterDate(localDate);
        return boxRepo.save(cutBox);
    }

    @Override
    public Set<CutBox> getCutBoxes() {
        return new LinkedHashSet<>(boxRepo.findAll());
    }

    @Override
    public CutBox getCutBox(Long cutBoxId) {
        return boxRepo.findById(cutBoxId).get();
    }

    @Override
    public void deleteCutBox(Long cutBoxId) {
        CutBox cutBox = new CutBox();
        cutBox.setCutBoxId(cutBoxId);
        boxRepo.delete(cutBox);
    }


    @Override
    public List<CutBox> searchByResponsibleName(String name) {
        if (name != null) {
            return boxRepo.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<CutBox> searchByCutTypeName(String name) {
        if (name != null) {
            return boxRepo.findByCutTypeNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }


    public List<CutBox> getCutBoxesByDateRange(Date startDate, Date endDate) {
        return boxRepo.findByDateBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CutBox> findAll() {
        return (List<CutBox>) boxRepo.findAll();
    }

    @Override
    public void saveAll(List<CutBox> cutBoxes) {
        boxRepo.saveAll(cutBoxes);
    }



    @Override
    public Integer getTotalAmountForMiniCuts() {
        return boxRepo.getTotalAmountForMiniCuts();
    }
    @Override
    public Integer getTotalAmountForStandardCuts() {
        return boxRepo.getTotalAmountForStandardCuts();
    }
    @Override
    public Integer getTotalAmountForBiggyCuts() {
        return boxRepo.getTotalAmountForBiggyCuts();
    }



    @Override
    public Integer getTotalAmountForMiniCutsByCurrentMonth() {
        return boxRepo.getTotalAmountForMiniCutsByCurrentMonth();
    }
    @Override
    public Integer getTotalAmountForStandardCutsByCurrentMonth() {
        return boxRepo.getTotalAmountForStandardCutsByCurrentMonth();
    }
    @Override
    public Integer getTotalAmountForBiggyCutsByCurrentMonth() {
        return boxRepo.getTotalAmountForBiggyCutsByCurrentMonth();
    }




    @Override
    public Integer getTotalAmountForMiniCutsByPreviousMonth() {
        return boxRepo.getTotalAmountForMiniCutsByPreviousMonth();
    }
    @Override
    public Integer getTotalAmountForStandardCutsByPreviousMonth() {
        return boxRepo.getTotalAmountForStandardCutsByPreviousMonth();
    }
    @Override
    public Integer getTotalAmountForBiggyCutsByPreviousMonth() {
        return boxRepo.getTotalAmountForBiggyCutsByPreviousMonth();
    }



    @Override
    public Integer getTotalWeightForMiniCutsByCurrentMonth() {
        return boxRepo.getTotalWeightForMiniCutsByCurrentMonth();
    }
    @Override
    public Integer getTotalWeightForStandardCutsByCurrentMonth() {
        return boxRepo.getTotalWeightForStandardCutsByCurrentMonth();
    }
    @Override
    public Integer getTotalWeightForBiggyCutsByCurrentMonth() {
        return boxRepo.getTotalWeightForBiggyCutsByCurrentMonth();
    }




    @Override
    public Integer getTotalWeightForMiniCutsByPreviousMonth() {
        return boxRepo.getTotalWeightForMiniCutsByPreviousMonth();
    }
    @Override
    public Integer getTotalWeightForStandardCutsByPreviousMonth() {
        return boxRepo.getTotalWeightForStandardCutsByPreviousMonth();
    }
    @Override
    public Integer getTotalWeightForBiggyCutsByPreviousMonth() {
        return boxRepo.getTotalWeightForBiggyCutsByPreviousMonth();
    }










    public Integer getTotalAmountByCutType(String name) {
        Query query = entityManager.createQuery(
                "SELECT SUM(c.amount) FROM Item c WHERE c.cutType.name = :name");
        query.setParameter("name", name);
        return (Integer) query.getSingleResult();
    }

    @Override
    public List<CutBox> getCutBoxesByCuttingId(Long cuttingId) {
        return boxRepo.findByCuttingCuttingId(cuttingId);
    }

}