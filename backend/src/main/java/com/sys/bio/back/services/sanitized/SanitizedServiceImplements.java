package com.sys.bio.back.services.sanitized;

import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.sanitized.Sanitized;
import com.sys.bio.back.repositories.cutting.CuttingRepository;
import com.sys.bio.back.repositories.sanitized.SanitizedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SanitizedServiceImplements implements SanitizedService {

    @Autowired
    private SanitizedRepository sanitizedRepo;

    @Override
    public Sanitized addSanitized(Sanitized sanitized) {
        return sanitizedRepo.save(sanitized);
    }

    @Override
    public Sanitized updateSanitized(Sanitized sanitized) {
        return sanitizedRepo.save(sanitized);
    }

    @Override
    public Set<Sanitized> getSanitizeds() {
        return new LinkedHashSet<>(sanitizedRepo.findAll());
    }

    @Override
    public Sanitized getSanitized(Long sanitizedId) {
        return sanitizedRepo.findById(sanitizedId).get();
    }

    @Override
    public void deleteSanitized(Long sanitizedId) {
        Sanitized sanitized = new Sanitized();
        sanitized.setSanitizedId(sanitizedId);
        sanitizedRepo.delete(sanitized);
    }

    @Override
    public List<Sanitized> getSanitizedsByDateRange(Date startDate, Date endDate) {
        return sanitizedRepo.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<Sanitized> searchByResponsibleName(String name) {
        if (name != null) {
            return sanitizedRepo.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }




}
