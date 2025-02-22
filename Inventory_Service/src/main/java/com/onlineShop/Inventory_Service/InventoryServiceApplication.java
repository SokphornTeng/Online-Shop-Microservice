package com.onlineShop.Inventory_Service;

import com.onlineShop.Inventory_Service.Model.Inventory;
import com.onlineShop.Inventory_Service.Repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo){
		return args -> {
			Inventory inv = new Inventory();
			inv.setCountryCode("Iphone14");
			inv.setQuantity(100);

			Inventory inv1 = new Inventory();
			inv1.setCountryCode("Iphone15");
			inv1.setQuantity(10);

			inventoryRepo.save(inv);
			inventoryRepo.save(inv1);
		};
	}

}
