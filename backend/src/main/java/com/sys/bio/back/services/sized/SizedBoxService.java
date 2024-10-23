package com.sys.bio.back.services.sized;

import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.sized.SizedBox;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SizedBoxService {

    SizedBox addSizedBox(SizedBox sizedBox);
    SizedBox updateSizedBox(SizedBox sizedBox);
    Set<SizedBox> getSizedBoxes();
    public List<SizedBox> findAll();
    SizedBox getSizedBox(Long sizedBoxId);
    void deleteSizedBox(Long sizedBoxId);
    List<SizedBox> getSizedBoxesByDateRange(Date startDate, Date endDate);

    List<SizedBox> searchByResponsibleName(String name);

    void saveAll(List<SizedBox> sizedBoxes);

    List<SizedBox> getSizedBoxesBySizingId(Long sizingId);
}
