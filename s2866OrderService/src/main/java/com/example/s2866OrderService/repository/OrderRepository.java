package com.example.s2866OrderService.repository;

import com.example.s2866OrderService.entity.Item;
import com.example.s2866OrderService.entity.Order;
import com.example.s2866OrderService.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    //TODO


    private final ItemRepository itemRepository;

    @Autowired
    public OrderRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Order> getOrders() {


        var items1 = itemRepository.getItems();

        orders.add(Order.builder().id(1).userId(1).orderStatus(OrderStatus.NEW).address("harfowa 19").items(List.of(items1.get(1),items1.get(3))).build());
        orders.add(Order.builder().id(2).userId(2).orderStatus(OrderStatus.NEW).address("harfowa 11").items(List.of(items1.get(5),items1.get(2))).build());
        orders.add(Order.builder().id(3).userId(3).orderStatus(OrderStatus.NEW).address("harfowa 231").items(List.of(items1.get(6),items1.get(7))).build());


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

        while (orders.iterator().hasNext()){
            if (orders.iterator().next().getId() == orderId){
                orders.iterator().remove();
                break;
            }
        }

    }

}