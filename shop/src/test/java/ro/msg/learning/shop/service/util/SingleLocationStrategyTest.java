package ro.msg.learning.shop.service.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class SingleLocationStrategyTest {
    @Mock
    private StockService stockService;
    @Mock
    private LocationService locationService;
    @InjectMocks
    private SingleLocationStrategy strategy;

    @Test
    public void Ok() {
        Supplier supplier = new Supplier(1, "Napolact");
        ProductCategory productCategory = new ProductCategory(1, "Lactate", "Produse din lapte");
        Product product1 = new Product(1, "Sana", "", new BigDecimal("13.4"), 500.0d, productCategory, supplier, "");
        Product product2 = new Product(2, "Lapte", "", new BigDecimal("13.4"), 500.0d, productCategory, supplier, "");
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Location location1 = new Location(1, "Auchan", "Romania", "Cluj-Napoca", "Cluj", "str.Alexandru nr.34");
        Location location2 = new Location(2, "Profi", "Romania", "Cluj-Napoca", "Cluj", "ale.Detunata nr.1");
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        Stock stockP2L1 = new Stock(new StockID(product2.getProductId(), location1.getLocationId()), 50, product2, location1);
        Stock stockP1L2 = new Stock(new StockID(product1.getProductId(), location2.getLocationId()), 50, product1, location2);
        Stock stockP2L2 = new Stock(new StockID(product2.getProductId(), location2.getLocationId()), 100, product2, location2);
        List<Stock> stocksL1 = new ArrayList<>();
        stocksL1.add(stockP2L1);
        List<Stock> stocksL2 = new ArrayList<>();
        stocksL2.add(stockP1L2);
        stocksL2.add(stockP2L2);
        HashMap<Product, Integer> orderedQuantity = new HashMap<>();
        orderedQuantity.put(product1, 30);
        orderedQuantity.put(product2, 20);

        Mockito.when(locationService.findAll()).thenReturn(locations);
        Mockito.when(stockService.findByLocation(location1)).thenReturn(stocksL1);
        Mockito.when(stockService.findByLocation(location2)).thenReturn(stocksL2);
        assertNotNull(strategy.execute(products, orderedQuantity));
    }

    @Test(expected = InsufficientStockException.class)
    public void InsufficientStock() throws ProductNotFoundException, InsufficientStockException {
        Supplier supplier = new Supplier(1, "Napolact");
        ProductCategory productCategory = new ProductCategory(1, "Lactate", "Produse din lapte");
        Product product1 = new Product(1, "Sana", "", new BigDecimal("13.4"), 500.0d, productCategory, supplier, "");
        Product product2 = new Product(2, "Lapte", "", new BigDecimal("13.4"), 500.0d, productCategory, supplier, "");
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Location location1 = new Location(1, "Auchan", "Romania", "Cluj-Napoca", "Cluj", "str.Alexandru nr.34");
        Location location2 = new Location(2, "Profi", "Romania", "Cluj-Napoca", "Cluj", "ale.Detunata nr.1");
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        Stock stockP1L1 = new Stock(new StockID(product1.getProductId(), location1.getLocationId()), 100, product1, location1);
        Stock stockP2L1 = new Stock(new StockID(product2.getProductId(), location1.getLocationId()), 50, product2, location1);
        Stock stockP1L2 = new Stock(new StockID(product1.getProductId(), location2.getLocationId()), 50, product1, location2);
        Stock stockP2L2 = new Stock(new StockID(product2.getProductId(), location2.getLocationId()), 100, product2, location2);
        List<Stock> stocksL1 = new ArrayList<>();
        stocksL1.add(stockP1L1);
        stocksL1.add(stockP2L1);
        List<Stock> stocksL2 = new ArrayList<>();
        stocksL2.add(stockP1L2);
        stocksL2.add(stockP2L2);
        HashMap<Product, Integer> orderedQuantity = new HashMap<>();
        orderedQuantity.put(product1, 30);
        orderedQuantity.put(product2, 2000);

        Mockito.when(locationService.findAll()).thenReturn(locations);
        Mockito.when(stockService.findByLocation(location1)).thenReturn(stocksL1);
        Mockito.when(stockService.findByLocation(location2)).thenReturn(stocksL2);
        strategy.execute(products, orderedQuantity);
    }
}
