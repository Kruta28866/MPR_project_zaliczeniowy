package com.example.s2866OrderService.controller;

import com.example.s2866OrderService.model.OrderRequest;
import com.example.s2866OrderService.model.OrderResponse;
import com.example.s2866OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//ta klasa bedzie wystawiala pewien server local host , jak sie połącze i zrobie localhost:8080/api to teraz bede mógł się dostawać do rzeczy do api
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
    @GetMapping("deliver/{orderId}")// odpowiednik geta sciezka do geta
    public ResponseEntity<OrderResponse> getIsDelivered(@PathVariable("orderId") int orderId) {
        var order = orderService.getIsDelivered(orderId);

        return order.map(value -> ResponseEntity.ok(OrderResponse.builder()
                .id(value.getId())
                .items(value.getItems())
                .orderStatus(value.getOrderStatus())
                .address(value.getAddress())
                .build())).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping("create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {

        var didSave = orderService.createOrder(request.getId(), request.getUserId(), request.getItems(), request.getAddress());

        if (didSave) {
            return ResponseEntity.ok("Order has been created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order was placed incorrectly");
    }
    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") int orderId){

        var message = orderService.deleteOrder(orderId);

        return ResponseEntity.ok(message);
    }
}
