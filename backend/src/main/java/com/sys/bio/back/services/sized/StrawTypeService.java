package com.sys.bio.back.services.sized;

import com.sys.bio.back.models.sized.StrawType;

import java.util.List;
import java.util.Set;

public interface StrawTypeService {

    StrawType addStrawType(StrawType strawType);
    StrawType updateStrawType(StrawType strawType);
    Set<StrawType> getStrawTypes();
    StrawType getStrawType(Long strawTypeId);
    void deleteStrawType(Long strawTypeId);
    void toggleStrawTypeStatus(Long strawTypeId, boolean newStatus);
    List<StrawType> findAll();
}
