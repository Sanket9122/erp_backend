package com.Sanket.ERP.services.imp;

import com.Sanket.ERP.entity.Accesories;
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
        return accessoryRepository.save(accessory);
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