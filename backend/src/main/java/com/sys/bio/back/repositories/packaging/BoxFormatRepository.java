package com.sys.bio.back.repositories.packaging;

import com.sys.bio.back.models.packaging.BoxFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoxFormatRepository extends JpaRepository<BoxFormat, Long> {
    List<BoxFormat> findByBoxName_boxNameId(Long boxNameId);
}
