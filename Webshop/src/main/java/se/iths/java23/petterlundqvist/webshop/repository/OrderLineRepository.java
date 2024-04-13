package se.iths.java23.petterlundqvist.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.java23.petterlundqvist.webshop.model.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
