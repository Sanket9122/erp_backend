package com.Sanket.ERP.services;

import com.Sanket.ERP.entity.Accesories;
import com.Sanket.ERP.entity.Project;

import java.util.List;

public interface AccesoryService {
    Accesories saveAccessory(Accesories accessory);

    Accesories addAccessory(Accesories accessory);

    List<Accesories> findByAssignedProject(Project project);

    long countByIsAvailableTrue(Accesories accesory);

    long countByAssignedProject(Accesories accesory);

    List<Accesories> getInStockItems();
    Accesories getBySerial(String serialNumber);
}