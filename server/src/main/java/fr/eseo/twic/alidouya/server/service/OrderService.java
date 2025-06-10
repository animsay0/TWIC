package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.model.Order;
import fr.eseo.twic.alidouya.server.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updated) {
        return orderRepository.findById(id)
                .map(existing -> {
                    existing.setOrderStatus(updated.getOrderStatus());
                    // Autres champs modifiables selon besoin
                    return orderRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
