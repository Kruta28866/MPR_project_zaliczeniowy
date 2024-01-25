package com.example.s2866OrderService.repository;

import com.example.s2866OrderService.entity.Order;
import com.example.s2866OrderService.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDao {

    private List<Order> orders = new ArrayList<>();

    //TODO


    private final ItemDao itemRepository;

    @Autowired
    public OrderDao(ItemDao itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Order> getOrders() {


        var items1 = itemRepository.getItems();

        orders.add(Order.builder().id(1).userId(1).orderStatus(OrderStatus.NEW).address("Gdańsk").items(List.of(items1.get(1),items1.get(3))).build());
        orders.add(Order.builder().id(2).userId(2).orderStatus(OrderStatus.NEW).address("Warszawa").items(List.of(items1.get(5),items1.get(2))).build());
        orders.add(Order.builder().id(3).userId(3).orderStatus(OrderStatus.NEW).address("Grudziądz").items(List.of(items1.get(6),items1.get(7))).build());
        orders.add(Order.builder().id(4).userId(4).orderStatus(OrderStatus.DELIVERED).address("Grudziądz").items(List.of(items1.get(4),items1.get(3))).build());
        orders.add(Order.builder().id(5).userId(5).orderStatus(OrderStatus.DELIVERED).address("poznan").items(List.of(items1.get(5),items1.get(2))).build());

        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Optional<Order> getOrderById(int orderID) {
        return getOrders()
                .stream()
                .filter(order -> order.getId() == orderID)
                .findFirst();
    }

    public void delete(int orderId) {

        int indexToRemove = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == orderId) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            orders.remove(indexToRemove);
        }
    }

}