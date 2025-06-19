package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.dto.OrderDTO;
import fr.eseo.twic.alidouya.server.dto.OrderDetailDTO;
import fr.eseo.twic.alidouya.server.dto.OrderFullDTO;
import fr.eseo.twic.alidouya.server.model.Order;
import fr.eseo.twic.alidouya.server.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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


    /*public Page<Order> getOrdersByCustomerAccountNo(String accountNo, Pageable pageable) {
        return orderRepository.findByAccountNoAccountNoOrderByOrderStatusIdAscPlacedTimestampDesc(accountNo, pageable);
    }*/

    public Page<OrderDTO> getOrdersByCustomerAccountNo(String accountNo, Pageable pageable) {
        return orderRepository.findByAccountNoAccountNoOrderByOrderStatusIdAscPlacedTimestampDesc(accountNo, pageable)
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getPlacedTimestamp(),
                        order.getOrderStatus().getOrderStatusName()
                ));
    }

/*
    public OrderFullDTO getOrderFullById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderDetailDTO> details = order.getOrderDetails().stream()
                .map(detail -> new OrderDetailDTO(
                        detail.getId(),
                        detail.getProduct().getProductNo(),
                        detail.getProduct().getProductName(),
                        detail.getQuantity()
                ))
                .toList();

        return new OrderFullDTO(
                order.getId(),
                order.getAccountNo().getAccountNo(),
                order.getOrderStatus().getOrderStatusName(),
                order.getPlacedTimestamp(),
                order.getDeliveredTimestamp(),
                order.getCancelledTimestamp(),
                order.getOrderDetails().stream()
                        .mapToDouble(d -> d.getQuantity() * d.getProduct().getPrice())
                        .sum(),
                details
        );

}
*/

    public OrderFullDTO getOrderFullById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderDetailDTO> details = order.getOrderDetails().stream()
                .map(detail -> new OrderDetailDTO(
                        detail.getId(),
                        detail.getProduct().getProductNo(),
                        detail.getProduct().getProductName(),
                        detail.getQuantity()
                ))
                .toList();

        BigDecimal total = order.getOrderDetails().stream()
                .map(d -> d.getProduct().getPrice().multiply(BigDecimal.valueOf(d.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderFullDTO(
                order.getId(),
                order.getAccountNo().getAccountNo(),
                order.getOrderStatus().getOrderStatusName(),
                order.getPlacedTimestamp(),
                order.getDeliveredTimestamp(),
                order.getCancelledTimestamp(),
                total.doubleValue(), // ✅ convert BigDecimal to double
                details
        );
    }

}
