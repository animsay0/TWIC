package fr.eseo.twic.alidouya.server.repository;

import fr.eseo.twic.alidouya.server.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find order details by order ID or product ID

     List<OrderDetail> findByOrderId(Long orderId);

    // List<OrderDetail> findByProductId(Long productNo);

     Page<OrderDetail> findByOrderId(Long orderId, Pageable pageable);

}
