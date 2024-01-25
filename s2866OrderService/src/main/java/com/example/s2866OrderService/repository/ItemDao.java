package com.example.s2866OrderService.repository;

import com.example.s2866OrderService.entity.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ItemDao {

    private List<Item> items = new ArrayList<>();
    //TODO Wypełnić itemy i stworzyć jakies ordery.

    public List<Item> getItems() {
        items.add(Item.builder().id(1).name("cebula").quantity(1).build());
        items.add(Item.builder().id(2).name("papryka").quantity(2).build());
        items.add(Item.builder().id(3).name("maslo").quantity(3).build());
        items.add(Item.builder().id(4).name("mleko").quantity(20).build());
        items.add(Item.builder().id(5).name("ogorek").quantity(5).build());
        items.add(Item.builder().id(6).name("chleb").quantity(40).build());
        items.add(Item.builder().id(7).name("bulki").quantity(23).build());
        items.add(Item.builder().id(8).name("platki").quantity(66).build());
        items.add(Item.builder().id(9).name("buty").quantity(12).build());

        return items;
    }

    public Optional<Item> getitemByName(String name) {
        return getItems()
                .stream()
                .filter(item -> item.getName() == name)
                .findFirst();
    }
}
