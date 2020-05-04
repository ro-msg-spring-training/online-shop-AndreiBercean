package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/test")
@Profile("test")
public class TestController {

    private final CustomerService customerService;
    private final LocationService locationService;
    private final OrderDetailService orderDetailService;
    private final OrdersService ordersService;
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    private final RevenueService revenueService;
    private final StockService stockService;
    private final SupplierService supplierService;

    @PostMapping()
    public ResponseEntity<String> populate(){
        Customer customer = new Customer(2, "Andrei", "Bercean", "AndreiBercean", "Password", "andreibercean@yahoo.com");
        customerService.insert(customer);

        Supplier supplier = new Supplier(1, "Napolact");
        supplierService.insert(supplier);

        ProductCategory productCategory = new ProductCategory(1, "Lactate", "Produse din lapte");
        productCategoryService.insert(productCategory);

        productCategory = productCategoryService.findById(new ProductCategory(4, "Lactate", "Produse din lapte"));
        supplier = supplierService.findById(new Supplier(7, "Napolact"));

        Product product1 = new Product(41, "Sana", "", new BigDecimal("13.4"), 500.0d, productCategory, supplier, "");
        productService.insert(product1);
        Product product2 = new Product(42, "Lapte", "", new BigDecimal("18.2"), 1500.0d, productCategory, supplier, "");
        productService.insert(product2);

        Location location1 = new Location(5, "Auchan", "Romania", "Cluj-Napoca", "Cluj", "str.Alexandru nr.34");
        locationService.insert(location1);

        Stock stock1 = new Stock(new StockID(product1.getProductId(), location1.getLocationId()), 100, product1, location1);
        stockService.insert(stock1);
        Stock stock2 = new Stock(new StockID(product2.getProductId(), location1.getLocationId()), 50, product2, location1);
        stockService.insert(stock2);

        return ResponseEntity.ok("Population test completed");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAll(){
        List<Stock> stockList = stockService.findAll();
        for(Stock stock : stockList){
            stockService.delete(stock);
        }
        List<OrderDetail> orderDetailList = orderDetailService.findAll();
        for(OrderDetail orderDetail : orderDetailList){
            orderDetailService.delete(orderDetail);
        }

        List<Product> productList = productService.findAll();
        for(Product product : productList){
            productService.delete(product);
        }

        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        for(ProductCategory productCategory : productCategoryList){
            productCategoryService.delete(productCategory);
        }

        List<Supplier> supplierList = supplierService.findAll();
        for(Supplier supplier : supplierList){
            supplierService.delete(supplier);
        }

        List<Revenue> revenueList = revenueService.findAll();
        for(Revenue revenue : revenueList){
            revenueService.delete(revenue);
        }

        List<Orders> ordersList = ordersService.findAll();
        for(Orders orders : ordersList){
            ordersService.delete(orders);
        }

        List<Customer> customerList = customerService.findAll();
        for(Customer customer : customerList){
            customerService.delete(customer);
        }

        List<Location> locationList = locationService.findAll();
        for(Location location : locationList){
            locationService.delete(location);
        }

        return ResponseEntity.ok("Delete test completed");
    }
}
