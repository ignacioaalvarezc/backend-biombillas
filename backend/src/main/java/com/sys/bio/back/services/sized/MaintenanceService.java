package com.sys.bio.back.services.sized;

import com.sys.bio.back.models.sized.Maintenance;
import com.sys.bio.back.models.sized.Sizing;

import java.util.Set;

public interface MaintenanceService {

    Maintenance addMaintenance(Maintenance maintenance);
    Maintenance updateMaintenance(Maintenance maintenance);
    Set<Maintenance> getMaintenances();
    Maintenance getMaintenance(Long maintenanceId);
    void deleteMaintenance(Long maintenanceId);
}
