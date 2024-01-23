package com.example.s2866OrderService.service;


import com.example.s2866OrderService.entity.Item;
import com.example.s2866OrderService.entity.Order;
import com.example.s2866OrderService.repository.ItemRepository;
import com.example.s2866OrderService.repository.OrderRepository;
import com.example.s2866OrderService.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Component robi dokładnie to samo ale definiuje dla ludzi czytelnosc ze to jest service
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    //Dependency injection(Wzorzec projektowy) - szykuje daną klasę do kolejnej klasy nie robiąc nowej instancji
    @Autowired
    public OrderService(OrderRepository repository, ItemRepository itemRepository) {
        this.orderRepository = repository;
        this.itemRepository = itemRepository;
    }

    public void createOrder(int orderId, int userId, List<Item> items, String address) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Item list cannot be empty");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Delivery address is required");
        }
        if (areItemsAvailable(items)) {
            throw new IllegalArgumentException("All items are not available");
        }
        var order = Order.builder()
                .id(orderId)
                .userId(userId)
                .items(items)
                .address(address)
                .orderStatus(OrderStatus.NEW)
                .build();

        orderRepository.addOrder(order);

    }

    public Optional<Order> getOrder(int orderID) {

        return orderRepository.getOrderById(orderID);
    }

    public String deleteOrder(int orderID) {

        var order = orderRepository.getOrderById(orderID);
        if (order.isPresent() && order.get().getOrderStatus() == OrderStatus.NEW) {

            orderRepository.delete(orderID);
            return "Zamówienie zostało anulowane.";
        }
        return "Zamówienia nie da się anulować";

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
