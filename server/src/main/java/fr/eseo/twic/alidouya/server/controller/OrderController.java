package fr.eseo.twic.alidouya.server.controller;

import fr.eseo.twic.alidouya.server.config.ApiRoutes;
import fr.eseo.twic.alidouya.server.dto.OrderDTO;
import fr.eseo.twic.alidouya.server.dto.OrderFullDTO;
import fr.eseo.twic.alidouya.server.model.Order;
import fr.eseo.twic.alidouya.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ApiRoutes.BASE + "/orders")
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

    /*@GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }*/

    @GetMapping("/{id}")
    public OrderFullDTO getOrderFull(@PathVariable Long id) {
        return orderService.getOrderFullById(id);
    }

    @PostMapping
    public ResponseEntity<OrderFullDTO> createOrder(@RequestBody OrderFullDTO dto) {
        Order created = orderService.createOrder(dto);
        return ResponseEntity.ok(orderService.getOrderFullById(created.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderFullDTO> updateOrder(@PathVariable Long id, @RequestBody OrderFullDTO dto) {
        Order updated = orderService.updateOrder(id, dto);
        return ResponseEntity.ok(orderService.getOrderFullById(updated.getId()));
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/customer/{accountNo}")
    public Page<OrderDTO> getOrdersByCustomerAccountNo(@PathVariable String accountNo, Pageable pageable) {
        return orderService.getOrdersByCustomerAccountNo(accountNo, pageable);
    }


}
