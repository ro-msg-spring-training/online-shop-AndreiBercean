package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Stock findById(Stock stock){
        Optional<Stock> stockFound  = stockRepository.findById(stock.getId());
        return stockFound.orElseGet(Stock::new);
    }

    public List<Stock> findAll(){
        return stockRepository.findAll();
    }


    public List<Stock> findByProduct(Product product){
        return stockRepository.findStockByProduct(product);
    }


    public List<Stock> findByLocation(Location location){
        return stockRepository.findStockByLocation(location);
    }

    public Stock insert(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock update(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock delete(Stock stock) {
        stockRepository.deleteById(stock.getId());
        return stock;
    }
}
