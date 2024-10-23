package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.repositories.packaging.BoxNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class BoxNameServiceImpl implements BoxNameService {

    @Autowired
    private BoxNameRepository nameRepo;

    @Override
    public BoxName addBoxName(BoxName boxName) {
        return nameRepo.save(boxName);
    }

    @Override
    public BoxName updateBoxName(BoxName boxName) {
        return nameRepo.save(boxName);
    }

    @Override
    public Set<BoxName> getBoxNames() {
        return new LinkedHashSet<>(nameRepo.findAll());
    }

    @Override
    public BoxName getBoxName(Long boxNameId) {
        return nameRepo.findById(boxNameId).get();
    }

    @Override
    public void deleteBoxName(Long boxNameId) {
        BoxName boxName = new BoxName();
        boxName.setBoxNameId(boxNameId);
        nameRepo.delete(boxName);
    }

    @Override
    public List<BoxName> findAll() {
        return nameRepo.findAll();
    }

    @Override
    public List<BoxName> getBoxNamesByProvider(Long providerId) {
        return nameRepo.findByProviderId(providerId);
    }

}
