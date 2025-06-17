package fr.eseo.twic.alidouya.server.controller;

import fr.eseo.twic.alidouya.server.config.ApiRoutes;
import fr.eseo.twic.alidouya.server.model.OrderDetail;
import fr.eseo.twic.alidouya.server.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiRoutes.BASE + "/orders/{orderId}/orderDetails")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public Page<OrderDetail> getOrderDetailsByOrderId(
            @PathVariable Long orderId,
            Pageable pageable
    ) {
        return orderDetailService.getOrderDetailsByOrderIdPaged(orderId, (org.springframework.data.domain.Pageable) pageable);
    }
}
