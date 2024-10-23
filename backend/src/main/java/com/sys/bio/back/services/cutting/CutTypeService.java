package com.sys.bio.back.services.cutting;

import com.sys.bio.back.models.cutting.CutType;

import java.util.List;
import java.util.Set;

public interface CutTypeService {

    CutType addCutType(CutType cutType);

    CutType updateCutType(CutType cutType);

    Set<CutType> getCutTypes();

    public List<CutType> findAll();

    CutType getCutType(Long cutTypeId);

    void deleteCutType(Long cutTypeId);

    Double getMiniCutFactor();
    Double getStandardCutFactor();
    Double getBiggyCutFactor();

}
