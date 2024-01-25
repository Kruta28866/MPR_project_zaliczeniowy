package com.example.s2866OrderService.service;

import com.example.s2866OrderService.types.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceTest {
    @Autowired
    OrderService service;
    @Test
    void createOrder() {
        var result = service.createOrder(-1,-1, Collections.emptyList(),"costam");

        assertFalse(result);

    }

    @Test
    void getOrder() {
        var order = service.getOrder(1);
        assertTrue(order.isPresent());
        assertEquals(OrderStatus.NEW,order.get().getOrderStatus());
    }

    @Test
    void getIsDelivered() {
        var order = service.getOrder(4);
        assertTrue(order.isPresent());
        assertEquals(OrderStatus.DELIVERED,order.get().getOrderStatus());
    }
}