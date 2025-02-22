package com.onlineShop.Order_Service.Service;


import com.onlineShop.Order_Service.Dto.InventoryResponse;
import com.onlineShop.Order_Service.Dto.OrderLineDto;
import com.onlineShop.Order_Service.Dto.OrderRequest;
import com.onlineShop.Order_Service.Event.OrderPlacedEvent;
import com.onlineShop.Order_Service.Model.Order;
import com.onlineShop.Order_Service.Model.OrderLine;
import com.onlineShop.Order_Service.Repository.OrderRepo;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepo orderRepo;

    private final WebClient.Builder webClientBuilder;


    private final ObservationRegistry observationRegistry;
    private final ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    public OrderService(OrderRepo orderRepo, WebClient.Builder webClientBuilder, ObservationRegistry observationRegistry, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepo = orderRepo;
        this.webClientBuilder = webClientBuilder;
        this.observationRegistry = observationRegistry;
        this.applicationEventPublisher = applicationEventPublisher;
    }


//    public Order createOrder(OrderRequest orderRequest) {
//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//
//        List<OrderLine> orderLines = orderRequest.getOrderLineDto().stream().map(OrderLineDto::mapToOrder).toList();
//
//        order.setOrderLine(orderLines);
//
//        try {
//            return this.orderRepo.save(order);
//        }catch (Exception e){
//            throw new RuntimeException("Error while saving order", e);
//        }
//    }



//    public Order CreateOrder(OrderRequest orderRequest) {
//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//        List<OrderLine> myOrderLineItem = orderRequest.getOrderLineDto()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
//        order.setOrderLine(myOrderLineItem);
//
//        List<String> countryCode = order.getOrderLine().stream().map(OrderLine::getCountryCode).toList();
//
//        InventoryResponse[] invenRespArrray =  webClientBuilder.build().get()
//                .uri("http://localhost:8082/api/inventory",
//                        uriBuilder -> uriBuilder.queryParam("countryCode", countryCode).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block();
//
//        boolean allProInStock = Arrays.stream(invenRespArrray).allMatch(InventoryResponse::isInStock);
//
//        if(allProInStock){
//            this.orderRepo.save(order);
//        }else {
//            throw new IllegalArgumentException("Product is not in stock, please try again!");
//        }
//        return order;
//    }
//
//


    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLine> orderLineItems = orderRequest.getOrderLineDto()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLine(orderLineItems);

        List<String> skuCodes = order.getOrderLine().stream()
                .map(OrderLine::getCountryCode)
                .toList();

        // Call Inventory Service, and place order if product is in
        // stock
        Observation inventoryServiceObservation = Observation.createNotStarted("inventory-service-lookup",
                this.observationRegistry);
        inventoryServiceObservation.lowCardinalityKeyValue("call", "inventory-service");
        return inventoryServiceObservation.observe(() -> {
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);

            if (allProductsInStock) {
                orderRepo.save(order);
                // publish Order Placed Event
                applicationEventPublisher.publishEvent(new OrderPlacedEvent(this, order.getOrderNumber()));
                return "Order Placed";
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        });

    }


    private OrderLine mapToDto(OrderLineDto itemDto){
        OrderLine lineItems = new OrderLine();
        lineItems.setPrice(itemDto.getPrice());
        lineItems.setQuantity(itemDto.getQuantity());
        lineItems.setCountryCode(itemDto.getCountryCode());
        return lineItems;
    }

}
