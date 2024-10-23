package com.sys.bio.back.services.packaging;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.packaging.Package;
import com.sys.bio.back.repositories.packaging.PackageRepository;
import com.sys.bio.back.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class PackageServiceImplements implements PackageService {

    @Autowired
    private PackageRepository packRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public Package addPackage(Package pack) {
        Date date = pack.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        pack.setFilterDate(localDate);
        return packRepo.save(pack);
    }

    @Override
    public Package updatePackage(Package pack) {
        Date date = pack.getDate();
        LocalDate localDate = DateUtils.convertToLocalDate(date);
        pack.setFilterDate(localDate);
        return packRepo.save(pack);
    }

    @Override
    public Set<Package> getPackages() {
        return new LinkedHashSet<>(packRepo.findAll());
    }

    @Override
    public Package getPackage(Long packageId) {
        return packRepo.findById(packageId).get();
    }

    @Override
    public void deletePackage(Long packageId) {
        Package pack = new Package();
        pack.setPackageId(packageId);
        packRepo.delete(pack);
    }


    @Override
    public List<Package> searchByResponsibleName(String name) {
        if (name != null) {
            return packRepo.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Package> getPackagesByDateRange(Date startDate, Date endDate) {
        return packRepo.findByDateBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Package> findAll() {
        return (List<Package>) packRepo.findAll();
    }

    @Override
    public void saveAll(List<Package> packages) {
        packRepo.saveAll(packages);
    }


    @Override
    public List<Package> getPackagesByPackagingId(Long packagingId) {
        return packRepo.findByPackagingPackagingId(packagingId);
    }

}
