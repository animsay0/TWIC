package fr.eseo.twic.alidouya.server.controller;

import fr.eseo.twic.alidouya.server.config.ApiRoutes;
import fr.eseo.twic.alidouya.server.model.Order;
import fr.eseo.twic.alidouya.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ApiRoutes.BASE+ "/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updated) {
        return orderService.updateOrder(id, updated);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/customer/{accountNo}")
    public Page<Order> getOrdersByCustomerAccountNo(@PathVariable String accountNo, Pageable pageable) {
        return orderService.getOrdersByCustomerAccountNo(accountNo, pageable);
    }


}
