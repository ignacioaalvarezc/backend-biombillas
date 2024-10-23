package com.sys.bio.back.repositories.packaging;

import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.packaging.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository <Package, Long>{

    List<Package> findByDateBetween(Date startDate, Date endDate);

    List<Package> findByResponsibleNameContainingIgnoreCase(String name);

    List<Package> findByPackagingPackagingId(Long packagingId);
}
