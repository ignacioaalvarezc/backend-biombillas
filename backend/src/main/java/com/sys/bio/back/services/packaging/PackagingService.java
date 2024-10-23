package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.Packaging;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PackagingService {

    Packaging addPackaging(Packaging packaging);
    Packaging updatePackaging(Packaging packaging);
    Set<Packaging> getPackagings();
    Packaging getPackaging(Long packagingId);
    void deletePackaging(Long packagingId);
    List<Packaging> getPackagingsByDateRange(Date startDate, Date endDate);
    List<Packaging> searchByResponsibleName(String name);

}
