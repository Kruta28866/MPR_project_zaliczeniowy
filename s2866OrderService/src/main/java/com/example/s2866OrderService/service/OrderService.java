package com.example.s2866OrderService.service;


import com.example.s2866OrderService.entity.Item;
import com.example.s2866OrderService.entity.Order;
import com.example.s2866OrderService.repository.ItemDao;
import com.example.s2866OrderService.repository.OrderDao;
import com.example.s2866OrderService.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Component robi dokładnie to samo ale definiuje dla ludzi czytelnosc ze to jest service
@Service
public class OrderService {
    private final OrderDao orderRepository;
    private final ItemDao itemRepository;


    //Dependency injection(Wzorzec projektowy) - szykuje daną klasę do kolejnej klasy nie robiąc nowej instancji
    @Autowired
    public OrderService(OrderDao repository, ItemDao itemRepository) {
        this.orderRepository = repository;
        this.itemRepository = itemRepository;
    }

    public boolean createOrder(int orderId, int userId, List<Item> items, String address) {
        if (orderId <= 0) {
            return false;
        }
        if (userId <= 0) {
            return false;
        }
        if (items == null || items.isEmpty()) {
            return false;
        }
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        if (areItemsAvailable(items)) {
            return false;
        }
        var order = Order.builder()
                .id(orderId)
                .userId(userId)
                .items(items)
                .address(address)
                .orderStatus(OrderStatus.NEW)
                .build();

        orderRepository.addOrder(order);
        return true;

    }

    public Optional<Order> getOrder(int orderID) {

        return orderRepository.getOrderById(orderID);
    }

    public Optional<Order> getIsDelivered(int orderId) {
        var order = orderRepository.getOrderById(orderId);
        if (order.isPresent() && order.get().getOrderStatus() == OrderStatus.DELIVERED) {
            return order;
        }
        return Optional.empty();
    }

    public String deleteOrder(int orderID) {

        var order = orderRepository.getOrderById(orderID);
        if (order.isPresent() && order.get().getOrderStatus() == OrderStatus.NEW) {

            orderRepository.delete(orderID);
            return "Order Has been cancelled.";
        }
        return "Order can't be cancelled";

    }


    //sprawdza czy itemy są dostępny vertuje po wszystkich itemach
    private boolean areItemsAvailable(List<Item> items) {
        for (Item item : items) {
            Optional<Item> matchingItem = itemRepository.getitemByName(item.getName());
            if (matchingItem.isEmpty() || item.getQuantity() > matchingItem.get().getQuantity()) {
                return false;
            }
        }
        return true;
    }


}
