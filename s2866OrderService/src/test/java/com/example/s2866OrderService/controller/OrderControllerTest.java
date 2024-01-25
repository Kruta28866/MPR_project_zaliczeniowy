package com.example.s2866OrderService.controller;

import com.example.s2866OrderService.entity.Item;
import com.example.s2866OrderService.entity.Order;
import com.example.s2866OrderService.model.OrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getOrderStatus() throws Exception {

        mockMvc.perform(get("/api/status/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());
    }

    @Test
    void getIsDelivered() throws Exception {
        mockMvc.perform(get("/api/deliver/" + 4)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());
    }

    @Test
    void createOrder() throws Exception {
        List<Item> items = List.of(Item.builder().id(3).name("maslo").quantity(3).build(), Item.builder().id(9).name("buty").quantity(12).build() );
        var order = OrderRequest.builder().id(1233).userId(123).address("ad").items(items).build();


        mockMvc.perform(post("/api/create",order)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteOrder() throws Exception {
        mockMvc.perform(delete("/api/delete/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());
    }
}