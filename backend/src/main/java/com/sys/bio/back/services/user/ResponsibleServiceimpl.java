package com.sys.bio.back.services.user;

import com.sys.bio.back.models.user.Responsible;
import com.sys.bio.back.repositories.user.ResponsibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResponsibleServiceimpl implements ResponsibleService {

    @Autowired
    private ResponsibleRepository responsibleRepository;

    @Override
    public Responsible addResponsible(Responsible responsible) {
        return responsibleRepository.save(responsible);
    }

    @Override
    public Responsible updateResponsible(Responsible responsible) {
        return responsibleRepository.save(responsible);
    }

    @Override
    public Set<Responsible> getResponsibles() {
        return new LinkedHashSet<>(responsibleRepository.findAll());
    }

    @Override
    public Responsible getResponsible(Long responsibleId) {
        return responsibleRepository.findById(responsibleId).get();
    }

    @Override
    public void deleteResponsible(Long responsibleId) {
        Responsible responsible = new Responsible();
        responsible.setResponsibleId(responsibleId);
        responsibleRepository.delete(responsible);
    }

    @Override
    public void toggleResponsibleStatus(Long responsibleId, boolean newStatus) {
        Responsible responsible = responsibleRepository.findById(responsibleId).orElse(null);
        if (responsible != null) {
            responsible.setEnabled(newStatus);
            responsibleRepository.save(responsible);
        }
    }

    @Override
    public List<Responsible> findAll() {
        return responsibleRepository.findAll();
    }
}
