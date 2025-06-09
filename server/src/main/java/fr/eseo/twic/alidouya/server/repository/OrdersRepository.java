package main.java.fr.eseo.twic.alidouya.server.repository;


import main.java.fr.eseo.twic.alidouya.server.model.Orders;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
