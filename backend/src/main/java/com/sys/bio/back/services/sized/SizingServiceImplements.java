package com.sys.bio.back.services.sized;

import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.sized.Sizing;
import com.sys.bio.back.repositories.sized.SizingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SizingServiceImplements implements SizingService {

    @Autowired
    private SizingRepository sizingRepo;

    @Override
    public Sizing addSizing(Sizing sizing) {
        return sizingRepo.save(sizing);
    }

    @Override
    public Sizing updateSizing(Sizing sizing) {
        return sizingRepo.save(sizing);
    }

    @Override
    public Set<Sizing> getSizings() {
        return new LinkedHashSet<>(sizingRepo.findAll());
    }

    @Override
    public Sizing getSizing(Long sizingId) {
        return sizingRepo.findById(sizingId).get();
    }

    @Override
    public void deleteSizing(Long sizingId) {
        Sizing sizing = new Sizing();
        sizing.setSizingId(sizingId);
        sizingRepo.delete(sizing);
    }
}
