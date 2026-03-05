package com.Sanket.ERP.Controllers;

import com.Sanket.ERP.entity.Accesories;
import com.Sanket.ERP.services.imp.AccesoryServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accessories")
@RequiredArgsConstructor
public class AccesoryController {

    private final AccesoryServiceImp accessoryService;

    @PostMapping("add-accesory")
    public Accesories add(@RequestBody Accesories accessory) {
        return accessoryService.addAccessory(accessory);
    }

    @GetMapping("/in-stock")
    public List<Accesories> getAvailable() {
        return accessoryService.getInStockItems();
    }
}