package com.sys.bio.back.services.sized;

import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.sized.Sizing;

import java.util.Set;

public interface SizingService {

    Sizing addSizing(Sizing sizing);
    Sizing updateSizing(Sizing sizing);
    Set<Sizing> getSizings();
    Sizing getSizing(Long sizingId);
    void deleteSizing(Long sizingId);
}
