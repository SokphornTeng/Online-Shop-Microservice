package com.onlineShop.Order_Service.Controller;


import com.onlineShop.Order_Service.Dto.OrderRequest;
import com.onlineShop.Order_Service.Model.Order;
import com.onlineShop.Order_Service.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    @Autowired
    private final OrderService orderService;

//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
//    public Order placeOrder(@RequestBody OrderRequest orderRequest){
//       return this.orderService.CreateOrder(orderRequest);
//    }
    public CompletableFuture<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Placing Order");
        return CompletableFuture.supplyAsync(() -> orderService.CreateOrder(orderRequest));
    }


    // configuration of circuit breaker
    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        log.info("Cannot Place Order Executing Fallback logic");
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }


}
