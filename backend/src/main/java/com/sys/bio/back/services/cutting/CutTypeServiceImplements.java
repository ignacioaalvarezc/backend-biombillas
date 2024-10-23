package com.sys.bio.back.services.cutting;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.cutting.CutType;
import com.sys.bio.back.repositories.cutting.CutTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.OverridesAttribute;
import java.util.*;

@Service
public class CutTypeServiceImplements implements CutTypeService {

    @Autowired
    private CutTypeRepository typeRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    @Override
    public CutType addCutType(CutType cutType) {
        cutType.setEnabled(true);
        return typeRepo.save(cutType);
    }
    @Override
    public CutType updateCutType(CutType cutType) {
        return typeRepo.save(cutType);
    }

    @Override
    public Set<CutType> getCutTypes() {
        return new LinkedHashSet<>(typeRepo.findAll());
    }

    @Override
    public CutType getCutType(Long cutTypeId) {
        return typeRepo.findById(cutTypeId).get();
    }

    @Override
    public void deleteCutType(Long cutTypeId) {
        CutType cutType = new CutType();
        cutType.setCutTypeId(cutTypeId);
        typeRepo.delete(cutType);
    }

    @Override
    public List<CutType> findAll() {
        return  typeRepo.findAll();
    }


    @Override
    public Double getMiniCutFactor() {
        Optional<CutType> miniParameter = typeRepo.findByName("Mini");
        return miniParameter.map(CutType::getFactor).orElse(null);
    }

    @Override
    public Double getStandardCutFactor() {
        Optional<CutType> standardParameter = typeRepo.findByName("Standard");
        return standardParameter.map(CutType::getFactor).orElse(null);
    }

    @Override
    public Double getBiggyCutFactor() {
        Optional<CutType> biggyParameter = typeRepo.findByName("Biggy");
        return biggyParameter.map(CutType::getFactor).orElse(null);
    }




}