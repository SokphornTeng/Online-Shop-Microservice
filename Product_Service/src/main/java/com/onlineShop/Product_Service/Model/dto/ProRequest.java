package com.onlineShop.Product_Service.Model.dto;

import com.onlineShop.Product_Service.Model.Entity.Product;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ProRequest implements Serializable {

    private String name;
    private String description;
    private BigDecimal price;

    public ProRequest() {
    }

    public ProRequest(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product requestFromEntity(){
        Product pro = new Product();
        pro.setName(this.name);
        pro.setDescription(this.description);
        pro.setPrice(this.price);
        return pro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
