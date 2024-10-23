package com.sys.bio.back.services.packaging;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.packaging.Packaging;
import com.sys.bio.back.repositories.packaging.PackagingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PackagingServiceImpl implements PackagingService{

    @Autowired
    private PackagingRepository packagingRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    @Override
    public Packaging addPackaging(Packaging packaging) {
        return packagingRepo.save(packaging);
    }
    @Override
    public Packaging updatePackaging(Packaging packaging) {
        return packagingRepo.save(packaging);
    }

    @Override
    public Set<Packaging> getPackagings() {
        return new LinkedHashSet<>(packagingRepo.findAll());
    }

    @Override
    public Packaging getPackaging(Long packagingId) {
        return packagingRepo.findById(packagingId).get();
    }

    @Override
    public void deletePackaging(Long packagingId) {
        Packaging packaging = new Packaging();
        packaging.setPackagingId(packagingId);
        packagingRepo.delete(packaging);
    }


    @Override
    public List<Packaging> searchByResponsibleName(String name) {
        if (name != null) {
            return packagingRepo.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }


    public List<Packaging> getPackagingsByDateRange(Date startDate, Date endDate) {
        return packagingRepo.findByDateBetween(startDate, endDate);
    }
}
