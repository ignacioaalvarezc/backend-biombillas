package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.models.packaging.Provider;

import java.util.List;
import java.util.Set;

public interface BoxNameService {
    BoxName addBoxName(BoxName boxName);
    BoxName updateBoxName(BoxName boxName);
    Set<BoxName> getBoxNames();
    BoxName getBoxName(Long boxNameId);

    void deleteBoxName(Long boxNameId);
    List<BoxName> findAll();

    List<BoxName> getBoxNamesByProvider(Long providerId);
}
