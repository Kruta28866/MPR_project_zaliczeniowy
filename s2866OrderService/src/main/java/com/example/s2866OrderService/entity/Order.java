package com.example.s2866OrderService.entity;

import com.example.s2866OrderService.types.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString

public class Order {

    private int id;
    private int userId;
    private OrderStatus orderStatus;
    private String address;
    private List<Item> items;

}
