package com.onlineShop.Product_Service.Repository;


import com.onlineShop.Product_Service.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

//    Product existsByName(String name);

}
