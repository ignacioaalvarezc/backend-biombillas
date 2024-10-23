package com.sys.bio.back.services.sanitized;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.sanitized.SanitizedBox;
import com.sys.bio.back.repositories.sanitized.SanitizedBoxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class SanitizedBoxServiceImpl implements SanitizedBoxService {

    private final SanitizedBoxRepository boxRepo;

    @Autowired
    public SanitizedBoxServiceImpl(SanitizedBoxRepository boxRepo) {
        this.boxRepo = boxRepo;
    }

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public SanitizedBox addSanitizedBox(SanitizedBox sanitizedBox) {
        return boxRepo.save(sanitizedBox);
    }


    @Override
    public SanitizedBox updateSanitizedBox(SanitizedBox sanitizedBox) {
        return boxRepo.save(sanitizedBox);
    }

    @Override
    public Set<SanitizedBox> getSanitizedBoxes() {
        return new LinkedHashSet<>(boxRepo.findAll());
    }

    @Override
    public SanitizedBox getSanitizedBox(Long sanitizedBoxId) {
        return boxRepo.findById(sanitizedBoxId).get();
    }

    @Override
    public void deleteSanitizedBoxId(Long sanitizedBoxId) {
        SanitizedBox sanitizedBox = new SanitizedBox();
        sanitizedBox.setSanitizedBoxId(sanitizedBoxId);
        boxRepo.delete(sanitizedBox);
    }


    @Override
    public List<SanitizedBox> searchByResponsibleName(String name) {
        if (name != null) {
            return boxRepo.findByResponsibleNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }

    public List<SanitizedBox> getSanitizedBoxesByDateRange(Date startDate, Date endDate) {
        return boxRepo.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<SanitizedBox> findByState(String state) {
        return boxRepo.findByState(state);
    }

    @Override
    public void saveAll(List<SanitizedBox> sanitizedBoxes) {
        boxRepo.saveAll(sanitizedBoxes);
    }

    @Override
    public void updateAll(List<Long> ids, List<SanitizedBox> sanitizedBoxes) {
        if (ids.size() != sanitizedBoxes.size()) {
            throw new IllegalArgumentException("The number of IDs must match the number of sized boxes.");
        }

        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            SanitizedBox newSanitizedBoxData = sanitizedBoxes.get(i);

            SanitizedBox existingSanitizedBox = boxRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("SanitizedBox not found with id " + id));

            existingSanitizedBox.setWetWeight(newSanitizedBoxData.getWetWeight());
            existingSanitizedBox.setState(newSanitizedBoxData.getState());
            existingSanitizedBox.setBeginDryingTime(newSanitizedBoxData.getBeginDryingTime());
            existingSanitizedBox.setEndSanitizedTime(newSanitizedBoxData.getEndSanitizedTime());
            existingSanitizedBox.setDryWeightFinal(newSanitizedBoxData.getDryWeightFinal());
            existingSanitizedBox.setEndDryingTime(newSanitizedBoxData.getEndDryingTime());
            existingSanitizedBox.setHoursBetweenDryingBeginAndEnding((newSanitizedBoxData.getHoursBetweenDryingBeginAndEnding()));
            existingSanitizedBox.setWeightLossPercentage(newSanitizedBoxData.getWeightLossPercentage());

            boxRepo.save(existingSanitizedBox);
        }
    }

    @Override
    public List<SanitizedBox> getSanitizedBoxesBySanitizedId(Long sanitizedId) {
        return boxRepo.findBySanitizedSanitizedId(sanitizedId);
    }
}
