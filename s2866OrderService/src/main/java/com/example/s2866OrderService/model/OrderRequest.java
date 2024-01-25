package com.example.s2866OrderService.model;

import com.example.s2866OrderService.entity.Item;
import com.example.s2866OrderService.types.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class OrderRequest {

    private int id;
    private int userId;
    private String address;
    private List<Item> items;
}
