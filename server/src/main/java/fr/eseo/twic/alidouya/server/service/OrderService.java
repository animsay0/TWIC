package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.dto.OrderDTO;
import fr.eseo.twic.alidouya.server.dto.OrderDetailDTO;
import fr.eseo.twic.alidouya.server.dto.OrderFullDTO;
import fr.eseo.twic.alidouya.server.model.*;
import fr.eseo.twic.alidouya.server.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository , CustomerRepository customerRepository,
                        OrderStatusRepository orderStatusRepository,ProductRepository productRepository,
                        OrderDetailRepository orderDetailRepository)  {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(OrderFullDTO dto) {
        Customer customer = customerRepository.findByAccountNo(dto.accountNo())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        OrderStatus status = orderStatusRepository.findByOrderStatusName("PLACED")
                .orElseThrow(() -> new RuntimeException("Order status 'PLACED' not found"));

        Order order = new Order();
        order.setAccountNo(customer);
        order.setOrderStatus(status);
        order.setPlacedTimestamp(Instant.now());

        // Sauver d'abord l'order pour générer l'id (si besoin)
        order = orderRepository.save(order);

        Order finalOrder = order;
        Set<OrderDetail> details = dto.orderDetails().stream()
                .map(d -> {
                    Product product = productRepository.findProductByProductNo(d.productNo())
                            .orElseThrow(() -> new RuntimeException("Product not found: " + d.productNo()));

                    OrderDetail detail = new OrderDetail();
                    detail.setOrder(finalOrder);
                    detail.setProduct(product);
                    detail.setQuantity(d.quantity());
                    return detail;
                })
                .collect(Collectors.toSet());

        orderDetailRepository.saveAll(details);
        order.setOrderDetails(details);

        return order;
    }

    public Order updateOrder(Long id, OrderFullDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getOrderStatus().getOrderStatusName().equals("PLACED")) {
            // si déjà livré/annulé → pas modifiable
            throw new RuntimeException("Cannot modify order with status " + order.getOrderStatus().getOrderStatusName());
        }

        OrderStatus newStatus = orderStatusRepository.findByOrderStatusName(dto.orderStatus())
                .orElseThrow(() -> new RuntimeException("Order status not found: " + dto.orderStatus()));

        order.setOrderStatus(newStatus);

        if ("DELIVERED".equals(dto.orderStatus())) {
            order.setDeliveredTimestamp(Instant.now());
        } else if ("CANCELLED".equals(dto.orderStatus())) {
            order.setCancelledTimestamp(Instant.now());
        }

        return orderRepository.save(order);
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
