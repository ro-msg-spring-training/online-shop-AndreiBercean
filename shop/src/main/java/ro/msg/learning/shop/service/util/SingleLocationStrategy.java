package ro.msg.learning.shop.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SingleLocationStrategy implements SearchStrategy {
    private final StockService stockService;
    private final LocationService locationService;

    public HashMap<Product, Stock> execute(List<Product> products, HashMap<Product, Integer> orderedQuantity) {
        HashMap<Product, Stock> stocks = new HashMap<>();
        List<Location> locations = locationService.findAll();
        for(Location location : locations){
            List<Stock> locationStocks = stockService.findByLocation(location);
            for(Product product : products){
                boolean found = false;
                for(Stock stock : locationStocks){
                    if(stock.getProduct().equals(product) && stock.getQuantity() > orderedQuantity.get(product)){
                        found = true;
                        stocks.put(product, stock);
                    }
                }
                if(!found){
                    stocks.clear();
                    break;
                }
            }
            if(products.size() == stocks.size())break;
        }
        if(products.size() != stocks.size())throw new InsufficientStockException("Not enough items in stock");
        return stocks;
    }
}
