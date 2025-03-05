package com.onlineShop.Inventory_Service.Service;


import com.onlineShop.Inventory_Service.Dto.InventoryResponse;
import com.onlineShop.Inventory_Service.Repository.InventoryRepo;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Data
public class InventoryService {


    @Autowired
    private final InventoryRepo inventoryRepo;

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

//    @Transactional(readOnly = true)
    ////    public List<Inventory> isInStock(List<String> countryCode){
    ////        return this.inventoryRepo.findByCountryCode(countryCode);
    ////    }

//    @Transactional(readOnly = true)
//      public Inventory isInStock(String countryCode){
//        return this.inventoryRepo.findBySkuCode(countryCode).orElseThrow();
//      }

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> countryCode) {
        return inventoryRepo.findByCountryCode(countryCode)
                .stream()
                .map(inv -> InventoryResponse.builder()
                        .countryCode(inv.getCountryCode())
                        .isInStock(inv.getQuantity() > 0)
                ).toList();


//        return inventoryRepo.findBySkuCodeIn(skuCode).stream()
//                .map(inventory ->
//                        InventoryResponse.builder()
//                                .countryCode(inventory.getCountryCode())
//                                .isInStock(inventory.getQuantity() > 0)
//                                .build()
//                ).toList();


    }

}
