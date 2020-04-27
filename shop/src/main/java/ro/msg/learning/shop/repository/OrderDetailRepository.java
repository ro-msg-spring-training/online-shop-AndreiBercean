package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.*;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailID> {

    @Query("SELECT detail FROM OrderDetail detail  WHERE detail.product = ?1")
    List<OrderDetail> findByProduct(Product product);

    @Query("SELECT detail FROM OrderDetail detail  WHERE detail.orders = ?1")
    List<OrderDetail> findByOrders(Orders orders);
}