package com.onlineShop.Product_Service.Controller;


import com.onlineShop.Product_Service.Model.Entity.Product;
import com.onlineShop.Product_Service.Model.dto.ProRequest;
import com.onlineShop.Product_Service.Model.dto.ProResponse;
import com.onlineShop.Product_Service.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class Controller {

    @Autowired
    private final ProductService productService;

    public Controller(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProResponse> createProduct(@RequestBody ProRequest proRequest){
       Product pro = productService.createPro(proRequest);
       return ResponseEntity.ok(ProResponse.mapToEntity(pro));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProResponse> getAllPro(){
        return productService.getAllPro();
    }


}
