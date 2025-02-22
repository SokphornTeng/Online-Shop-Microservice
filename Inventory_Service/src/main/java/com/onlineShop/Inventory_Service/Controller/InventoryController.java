package com.onlineShop.Inventory_Service.Controller;


import com.onlineShop.Inventory_Service.Dto.InventoryResponse;
import com.onlineShop.Inventory_Service.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

//    @GetMapping("/{skuCode}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock( String countryCode) {
//        return inventoryService.isInStock(countryCode);
//    }

//    @GetMapping("/{countryCode}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("countryCode") String countryCode) {
//       return inventoryService.isInStock(countryCode);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStocks(@RequestParam List<String> countryCode) {
        return inventoryService.isInStock(countryCode);
    }
}