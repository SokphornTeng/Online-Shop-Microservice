package com.onlineShop.Order_Service.Dto;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderLineDto> orderLineDto;

    public List<OrderLineDto> getOrderLineDto() {
        return orderLineDto;
    }

    public void setOrderLineDto(List<OrderLineDto> orderLineDto) {
        this.orderLineDto = orderLineDto;
    }
}
