package ro.msg.learning.shop.service.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import ro.msg.learning.shop.dto.OrderDetailDTO;
import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.OrderDetailService;
import ro.msg.learning.shop.service.OrdersService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class MostAbundantStrategyTest {
    @Mock
    private StockService stockService;
    @InjectMocks
    MostAbundantStrategy strategy;

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
        Stock stockP1L1 = new Stock(new StockID(product1.getProductId(), location1.getLocationId()), 100, product1, location1);
        Stock stockP2L1 = new Stock(new StockID(product2.getProductId(), location1.getLocationId()), 50, product2, location1);
        Stock stockP1L2 = new Stock(new StockID(product1.getProductId(), location2.getLocationId()), 50, product1, location2);
        Stock stockP2L2 = new Stock(new StockID(product2.getProductId(), location2.getLocationId()), 100, product2, location2);
        List<Stock> stocksP1 = new ArrayList<>();
        stocksP1.add(stockP1L1);
        stocksP1.add(stockP1L2);
        List<Stock> stocksP2 = new ArrayList<>();
        stocksP2.add(stockP2L1);
        stocksP2.add(stockP2L2);
        HashMap<Product, Integer> orderedQuantity = new HashMap<>();
        orderedQuantity.put(product1, 30);
        orderedQuantity.put(product2, 20);

        Mockito.when(stockService.findByProduct(product1)).thenReturn(stocksP1);
        Mockito.when(stockService.findByProduct(product2)).thenReturn(stocksP2);
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
        Stock stockP1L1 = new Stock(new StockID(product1.getProductId(), location1.getLocationId()), 100, product1, location1);
        Stock stockP2L1 = new Stock(new StockID(product2.getProductId(), location1.getLocationId()), 50, product2, location1);
        Stock stockP1L2 = new Stock(new StockID(product1.getProductId(), location2.getLocationId()), 50, product1, location2);
        Stock stockP2L2 = new Stock(new StockID(product2.getProductId(), location2.getLocationId()), 100, product2, location2);
        List<Stock> stocksP1 = new ArrayList<>();
        stocksP1.add(stockP1L1);
        stocksP1.add(stockP1L2);
        List<Stock> stocksP2 = new ArrayList<>();
        stocksP2.add(stockP2L1);
        stocksP2.add(stockP2L2);
        HashMap<Product, Integer> orderedQuantity = new HashMap<>();
        orderedQuantity.put(product1, 30);
        orderedQuantity.put(product2, 20000);

        Mockito.when(stockService.findByProduct(product1)).thenReturn(stocksP1);
        Mockito.when(stockService.findByProduct(product2)).thenReturn(stocksP2);
        strategy.execute(products, orderedQuantity);
    }
}
