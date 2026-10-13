package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.MaintenanceRepository;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance saveMaintenance(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException("L'identifiant doit être null");
        }
        if (maintenance.getDateDebut() != null && maintenance.getDateFin() != null &&
                maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance updateMaintenance(Long id, Maintenance maintenance) {
        Maintenance existant = getMaintenanceById(id);
        if (maintenance.getDateDebut() != null && maintenance.getDateFin() != null &&
                maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
        existant.setDateDebut(maintenance.getDateDebut());
        existant.setDateFin(maintenance.getDateFin());
        existant.setDescription(maintenance.getDescription());
        existant.setVehicule(maintenance.getVehicule());
        return maintenanceRepository.save(existant);
    }

    @Override
    public void deleteMaintenance(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        maintenanceRepository.deleteById(id);
    }
}