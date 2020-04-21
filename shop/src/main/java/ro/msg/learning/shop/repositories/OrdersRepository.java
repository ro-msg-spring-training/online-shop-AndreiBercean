package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
