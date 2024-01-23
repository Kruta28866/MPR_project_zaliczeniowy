package com.example.s2866OrderService.controller;

import com.example.s2866OrderService.model.OrderResponse;
import com.example.s2866OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController//ta klasa bedzie wystawiala pewien server local host , jak sie połącze i zrobie localhost:8080/api to teraz bede mógł się dostawać do rzeczy do api
@RequestMapping("/api")//bedzie sciezka do local hosta do controlera w webie
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("status/{orderId}")// odpowiednik geta sciezka do geta
    public ResponseEntity<OrderResponse> getOrderStatus(@PathVariable("orderId") int orderId) {
        var order = orderService.getOrder(orderId);
        return order.map(value -> ResponseEntity.ok(OrderResponse.builder()
                        .id(value.getId())
                        .items(value.getItems())
                        .orderStatus(value.getOrderStatus())
                        .address(value.getAddress())
                        .build()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
