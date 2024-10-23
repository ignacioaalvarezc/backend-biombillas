package com.sys.bio.back.repositories.packaging;

import com.sys.bio.back.models.packaging.Package;
import com.sys.bio.back.models.packaging.Packaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PackagingRepository extends JpaRepository<Packaging, Long> {

    List<Packaging> findByDateBetween(Date startDate, Date endDate);

    List<Packaging> findByResponsibleNameContainingIgnoreCase(String name);
}
