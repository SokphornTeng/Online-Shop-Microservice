package com.onlineShop.Order_Service.Dto;


import com.onlineShop.Order_Service.Model.OrderLine;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto implements Serializable {

    private Long id;
    private String countryCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderLine mapToOrder(){
        OrderLine orderL = new OrderLine();
        orderL.setCountryCode(this.countryCode);
        orderL.setPrice(this.price);
        orderL.setQuantity(this.quantity);
        return orderL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
