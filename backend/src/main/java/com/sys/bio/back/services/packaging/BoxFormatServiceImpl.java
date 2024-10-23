package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.BoxFormat;
import com.sys.bio.back.models.packaging.BoxType;
import com.sys.bio.back.repositories.packaging.BoxFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class BoxFormatServiceImpl implements BoxFormatService {

    @Autowired
    private BoxFormatRepository formatRepo;

    @Override
    public BoxFormat addBoxFormat(BoxFormat boxformat) {
        return formatRepo.save(boxformat);
    }

    @Override
    public BoxFormat updateBoxFormat(BoxFormat boxformat) {
        return formatRepo.save(boxformat);
    }

    @Override
    public Set<BoxFormat> getBoxFormats() {
        return new LinkedHashSet<>(formatRepo.findAll());
    }

    @Override
    public BoxFormat getBoxFormat(Long boxFormatId) {
        return formatRepo.findById(boxFormatId).get();
    }

    @Override
    public void deleteBoxFormat(Long boxFormatId) {
        BoxFormat boxFormat = new BoxFormat();
        boxFormat.setBoxFormatId(boxFormatId);
        formatRepo.delete(boxFormat);
    }

    @Override
    public List<BoxFormat> findAll() {
        return formatRepo.findAll();
    }

    @Override
    public List<BoxFormat> getBoxFormatsByBoxName(Long boxNameId) {
        return formatRepo.findByBoxName_boxNameId(boxNameId);
    }
}
