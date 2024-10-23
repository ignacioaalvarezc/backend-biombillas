package com.sys.bio.back.services.user;

import com.sys.bio.back.models.user.Responsible;

import java.util.List;
import java.util.Set;

public interface ResponsibleService {
    Responsible addResponsible(Responsible responsible);
    Responsible updateResponsible(Responsible responsible);
    Set<Responsible> getResponsibles();
    Responsible getResponsible(Long responsibleId);
    void deleteResponsible(Long responsibleId);
    void toggleResponsibleStatus(Long responsibleId, boolean newStatus);

    List<Responsible> findAll();

}
