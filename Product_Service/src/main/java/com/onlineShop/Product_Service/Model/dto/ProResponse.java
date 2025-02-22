package com.onlineShop.Product_Service.Model.dto;


import com.onlineShop.Product_Service.Model.Entity.Product;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ProResponse implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public ProResponse(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static ProResponse mapToEntity(Product pro) {
        return new ProResponse(pro.getId(), pro.getName(), pro.getDescription(), pro.getPrice());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
