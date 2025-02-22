package com.onlineShop.Product_Service.Service;


import com.onlineShop.Product_Service.Model.Entity.Product;
import com.onlineShop.Product_Service.Model.dto.ProRequest;
import com.onlineShop.Product_Service.Model.dto.ProResponse;
import com.onlineShop.Product_Service.Repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product createPro(ProRequest request){
        Product pro = new Product();
//        if(this.productRepo.existsByName(pro.getName()).equals(request.getName())){
//          throw new IllegalArgumentException("Product name already exists");
//        }
        pro.setName(request.getName());
        pro.setDescription(request.getDescription());
        pro.setPrice(request.getPrice());

       return this.productRepo.save(pro);
    }

    public List<ProResponse> getAllPro(){
        List<Product> proList = this.productRepo.findAll();
       return proList.stream().map(ProResponse::mapToEntity).toList();
    }

}
