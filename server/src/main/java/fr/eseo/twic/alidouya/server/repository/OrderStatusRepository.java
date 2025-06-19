package fr.eseo.twic.alidouya.server.repository;

import fr.eseo.twic.alidouya.server.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {


    Optional<OrderStatus> findById(Long id);

    Optional<OrderStatus> findByOrderStatusName(String orderStatusName);

}
