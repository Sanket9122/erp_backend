package com.Sanket.ERP.services;

import com.Sanket.ERP.entity.Accesories;
import java.util.List;

public interface AccesoryService {
    Accesories saveAccessory(Accesories accessory);

    Accesories addAccessory(Accesories accessory);

    List<Accesories> getInStockItems();
    Accesories getBySerial(String serialNumber);
}