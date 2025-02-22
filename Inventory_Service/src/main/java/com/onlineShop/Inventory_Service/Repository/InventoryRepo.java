package com.onlineShop.Inventory_Service.Repository;


import com.onlineShop.Inventory_Service.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

//    Optional<Inventory> findBySkuCode(String countryCode);
    @Query("SELECT i FROM Inventory i WHERE i.countryCode IN :countryCode")
    List<Inventory> findByCountryCode(List<String> countryCode);

}
