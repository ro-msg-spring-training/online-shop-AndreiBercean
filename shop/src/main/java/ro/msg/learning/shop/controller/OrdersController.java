package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDetailDTO;
import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.OrderDetailService;
import ro.msg.learning.shop.service.OrdersService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.util.SearchStrategy;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrdersController {
    private final SearchStrategy getStrategy;
    private final OrdersService ordersService;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final StockService stockService;

    private boolean contains(List<Orders> list, Orders order){
        for(Orders item : list){
            if(item.getShippedFrom().equals(order.getShippedFrom()))return true;
        }
        return false;
    }

    private Orders findOrder(List<Orders> orders, Location location){
        for(Orders order : orders){
            if(order.getShippedFrom().equals(location))return order;
        }
        return null;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<List<Orders>> insert(@RequestBody OrdersDTO ordersDTO) {
        Customer customer = new Customer(2, "Andrei", "Bercean", "AndreiBercean", "Password", "andreibercean@yahoo.com");
        List<Orders> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        HashMap<Product, Integer> orderedQuantity = new HashMap<>();
        for(OrderDetailDTO o : ordersDTO.getProducts()){
            if(productService.findById(o.getProductId())!=null) {
                Product product = productService.findById(o.getProductId());
                products.add(product);
                orderedQuantity.put(product, o.getQuantity());
            } else {
                throw new ProductNotFoundException("Product not found in database");
            }
        }
        HashMap<Product, Stock> stocks = getStrategy.execute(products, orderedQuantity);
        for(Product product : products){
            Orders o;
            Orders order = new Orders(null, null, customer, ordersDTO.getCreatedAt(), ordersDTO.getAddressCountry(), ordersDTO.getAddressCity(), ordersDTO.getAddressCounty(), ordersDTO.getAddressStreetAddress());
            Stock stock = stocks.get(product);
            stock.setQuantity(stock.getQuantity() - orderedQuantity.get(product));
            stockService.update(stock);
            order.setShippedFrom(stock.getLocation());
            if(!contains(orders, order)){
                o = ordersService.insert(order);
                orders.add(o);
            } else {
                o = findOrder(orders, stock.getLocation());
            }
            orderDetailService.insert(new OrderDetail(new OrderDetailID(o.getOrderId(), product.getProductId()), orderedQuantity.get(product), o, product));
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping()
    public ResponseEntity<List<Orders>> getAll() {
        return ResponseEntity.ok(ordersService.findAll());
    }
}
