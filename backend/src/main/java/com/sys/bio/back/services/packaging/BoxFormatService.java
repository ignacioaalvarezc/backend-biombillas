package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.BoxFormat;
import com.sys.bio.back.models.packaging.BoxType;

import java.util.List;
import java.util.Set;

public interface BoxFormatService {

    BoxFormat addBoxFormat(BoxFormat boxFormat);
    BoxFormat updateBoxFormat(BoxFormat boxFormat);
    Set<BoxFormat> getBoxFormats();
    BoxFormat getBoxFormat(Long boxFormatId);
    void deleteBoxFormat(Long boxFormatId);

    List<BoxFormat> findAll();

    List<BoxFormat> getBoxFormatsByBoxName(Long boxNameId);
}
