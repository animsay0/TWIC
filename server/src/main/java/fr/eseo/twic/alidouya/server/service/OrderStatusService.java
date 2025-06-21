package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.dto.OrderStatusDTO;
import fr.eseo.twic.alidouya.server.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {
    private final OrderStatusRepository repo;

    public OrderStatusService(OrderStatusRepository repo) {
        this.repo = repo;
    }

    public List<OrderStatusDTO> getAll() {
        return repo.findAll().stream()
                .map(s -> new OrderStatusDTO(s.getId(), s.getOrderStatusName()))
                .toList();
    }
}
