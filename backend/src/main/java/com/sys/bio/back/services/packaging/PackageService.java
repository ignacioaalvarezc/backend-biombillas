package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.Package;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PackageService {

    Package addPackage(Package pack);
    Package updatePackage(Package pack);
    Set<Package> getPackages();
    public List<Package> findAll();
    Package getPackage(Long packageId);
    void deletePackage(Long packageId);
    List<Package> getPackagesByDateRange(Date startDate, Date endDate);

    List<Package> searchByResponsibleName(String name);

    void saveAll(List<Package> packages);

    List<Package> getPackagesByPackagingId(Long packagingId);
}
