package ro.msg.learning.shop.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.StockService;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MostAbundantStrategy implements SearchStrategy {
    private final StockService stockService;

    public HashMap<Product, Stock> execute(List<Product> products, HashMap<Product, Integer> orderedQuantity){
        HashMap<Product, Stock> stocks = new HashMap<>();
        for(Product product : products){
            Stock max = new Stock();
            max.setQuantity(0);
            for(Stock stock : stockService.findByProduct(product)){
                if(stock.getQuantity() >= max.getQuantity())max = stock;
            }
            if(max.getQuantity() > orderedQuantity.get(product)){
                stocks.put(product, max);
            }
            else {
                throw new InsufficientStockException(max.getProduct().getName() + " was not in stock");
            }
        }
        return stocks;
    }
}
