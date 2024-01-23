package com.example.s2866OrderService.entity;
import lombok.*;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Item {

    private int id;

    private String name;

    private int quantity;

}
