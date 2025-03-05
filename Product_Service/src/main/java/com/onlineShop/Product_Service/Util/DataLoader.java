package com.onlineShop.Product_Service.Util;

import com.onlineShop.Product_Service.Model.Entity.Product;
import com.onlineShop.Product_Service.Repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

public class DataLoader implements CommandLineRunner {


    private final ProductRepo productRepository;

    public DataLoader(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() < 1) {
            Product product = new Product();
            product.setName("iPhone 13");
            product.setDescription("iPhone 13");
            product.setPrice(BigDecimal.valueOf(1000));

            productRepository.save(product);
        }
    }

}
