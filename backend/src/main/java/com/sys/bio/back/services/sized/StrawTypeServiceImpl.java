package com.sys.bio.back.services.sized;

import com.sys.bio.back.models.sized.StrawType;
import com.sys.bio.back.repositories.sized.StrawTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class StrawTypeServiceImpl implements StrawTypeService {

    @Autowired
    private StrawTypeRepository strawTypeRepository;

    @Override
    public StrawType addStrawType(StrawType strawType) {
        return strawTypeRepository.save(strawType);
    }

    @Override
    public StrawType updateStrawType(StrawType strawType) {
        return strawTypeRepository.save(strawType);
    }

    @Override
    public Set<StrawType> getStrawTypes() {
        return new LinkedHashSet<>(strawTypeRepository.findAll());
    }

    @Override
    public StrawType getStrawType(Long strawTypeId) {
        return strawTypeRepository.findById(strawTypeId).get();
    }

    @Override
    public void deleteStrawType(Long strawTypeId) {
        StrawType strawType = new StrawType();
        strawType.setStrawTypeId(strawTypeId);
        strawTypeRepository.delete(strawType);
    }

    @Override
    public void toggleStrawTypeStatus(Long strawTypeId, boolean newStatus) {
        StrawType strawType = strawTypeRepository.findById(strawTypeId).orElse(null);
        if (strawType != null) {
            strawType.setEnabled(newStatus);
            strawTypeRepository.save(strawType);
        }
    }

    @Override
    public List<StrawType> findAll() {
        return  strawTypeRepository.findAll();
    }
}
