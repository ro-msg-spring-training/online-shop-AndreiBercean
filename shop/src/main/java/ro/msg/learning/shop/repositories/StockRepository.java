package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.model.StockID;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockID> {
}
