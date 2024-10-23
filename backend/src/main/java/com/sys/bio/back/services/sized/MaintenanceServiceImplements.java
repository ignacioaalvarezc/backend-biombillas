package com.sys.bio.back.services.sized;

import com.sys.bio.back.models.sized.Maintenance;
import com.sys.bio.back.models.sized.Sizing;
import com.sys.bio.back.repositories.sized.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class MaintenanceServiceImplements implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepo;

    @Override
    public Maintenance addMaintenance(Maintenance maintenance) {
        return maintenanceRepo.save(maintenance);
    }

    @Override
    public Maintenance updateMaintenance(Maintenance maintenance) {
        return maintenanceRepo.save(maintenance);
    }

    @Override
    public Set<Maintenance> getMaintenances() {
        return new LinkedHashSet<>(maintenanceRepo.findAll());
    }

    @Override
    public Maintenance getMaintenance(Long maintenanceId) {
        return maintenanceRepo.findById(maintenanceId).get();
    }

    @Override
    public void deleteMaintenance(Long maintenanceId) {
        Maintenance maintenance = new Maintenance();
        maintenance.setMaintenanceId(maintenanceId);
        maintenanceRepo.delete(maintenance);
    }

}
