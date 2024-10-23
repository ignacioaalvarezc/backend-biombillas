package com.sys.bio.back.repositories.packaging;

import com.sys.bio.back.models.packaging.BoxName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BoxNameRepository extends JpaRepository<BoxName, Long> {
    @Query("SELECT b FROM BoxName b WHERE b.provider.providerId = :providerId")
    List<BoxName> findByProviderId(@Param("providerId") Long providerId);
}
