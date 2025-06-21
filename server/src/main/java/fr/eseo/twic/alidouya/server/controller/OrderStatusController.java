package fr.eseo.twic.alidouya.server.controller;

import fr.eseo.twic.alidouya.server.config.ApiRoutes;
import fr.eseo.twic.alidouya.server.dto.OrderStatusDTO;
import fr.eseo.twic.alidouya.server.service.OrderStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.BASE +"/order-statuses")
public class OrderStatusController {
    private final OrderStatusService service;

    public OrderStatusController(OrderStatusService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderStatusDTO> getAll() {
        return service.getAll();
    }
}
