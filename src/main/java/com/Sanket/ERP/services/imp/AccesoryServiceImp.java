package com.Sanket.ERP.services.imp;

import com.Sanket.ERP.entity.Accesories;
import com.Sanket.ERP.entity.Project;
import com.Sanket.ERP.repositories.AccessoryRepository;
import com.Sanket.ERP.services.AccesoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccesoryServiceImp implements AccesoryService {

    private final AccessoryRepository accessoryRepository;

    @Override
    public Accesories saveAccessory(Accesories accessory) {
        return accessoryRepository.save(accessory);
    }

    @Override
    public Accesories addAccessory(Accesories accessory) {
        return null;
    }

    @Override
    public List<Accesories> findByAssignedProject(Project project){
        return accessoryRepository.findByAssignedProject(project);
    }
    @Override
    public long countByIsAvailableTrue(Accesories accesory) {
        return accessoryRepository.countByIsAvailableTrue();
    }
    @Override
    public long countByAssignedProject(Accesories accesory){
        return accessoryRepository.countByAssignedProject(accesory.getAssignedProject());
    }
    @Override
    public List<Accesories> getInStockItems() {
        return accessoryRepository.findByIsAvailableTrue();
    }

    @Override
    public Accesories getBySerial(String serialNumber) {
        return accessoryRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new RuntimeException("Accessory not found"));
    }
}