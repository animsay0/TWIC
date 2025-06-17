package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.model.OrderDetail;
import fr.eseo.twic.alidouya.server.repository.OrderDetailRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public OrderDetail saveOrderDetail(OrderDetail detail) {
        return orderDetailRepository.save(detail);
    }

    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }

    public Page<OrderDetail> getOrderDetailsByOrderIdPaged(Long orderId, Pageable pageable) {
        return orderDetailRepository.findByOrderId(orderId, pageable);
    }
}
