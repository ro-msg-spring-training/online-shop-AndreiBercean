package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.model.StockID;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, StockID> {

    @Query("SELECT stock FROM Stock stock  WHERE stock.product = ?1")
    List<Stock> findStockByProduct(Product product);

    @Query("SELECT stock FROM Stock stock  WHERE stock.location = ?1")
    List<Stock> findStockByLocation(Location location);
}
