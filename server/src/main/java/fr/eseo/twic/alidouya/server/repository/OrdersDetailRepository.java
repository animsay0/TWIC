package fr.eseo.twic.alidouya.server.repository;

import fr.eseo.twic.alidouya.server.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailRepository extends JpaRepository<OrderDetail, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find order details by order ID or product ID
    // List<OrderDetail> findByOrderId(Long orderId);
    // List<OrderDetail> findByProductId(Long productId);
}
