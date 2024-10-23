package com.sys.bio.back.repositories.sized;

import com.sys.bio.back.models.sized.Sizing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SizingRepository extends JpaRepository<Sizing, Long>,
        JpaSpecificationExecutor<Sizing> {
}
