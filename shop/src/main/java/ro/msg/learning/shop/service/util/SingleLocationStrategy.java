package ro.msg.learning.shop.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDetailDTO;
import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SingleLocationStrategy implements SearchStrategy {
    private final OrdersService ordersService;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final StockService stockService;
    private final LocationService locationService;

    @Override
    public List<Orders> insert(OrdersDTO ordersDTO) throws ProductNotFoundException {
        Customer customer = new Customer(2, "Andrei", "Bercean", "AndreiBercean", "Password", "andreibercean@yahoo.com");
        List<Orders> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Location> locations = locationService.findAll();
        List<OrderDetailDTO> orderDetailDTOS = ordersDTO.getProducts();
        HashMap<Integer, Integer> orderedQuantity = new HashMap<>();
        for(OrderDetailDTO o : orderDetailDTOS){
            orderedQuantity.put(o.getProductId(), o.getQuantity());
            if(productService.findById(o.getProductId())!=null)
                products.add(productService.findById(o.getProductId()));
        }

        List<Stock> stocks = new ArrayList<>();
        for(Location location : locations){
            List<Stock> locationStocks = stockService.findByLocation(location);
            for(Product product : products){
                boolean found = false;
                for(Stock stock : locationStocks){
                    if(stock.getProduct().equals(product) && stock.getQuantity() > orderedQuantity.get(product.getProductId())){
                        found = true;
                        stocks.add(stock);
                    }
                }
                if(!found){
                    stocks.clear();
                    break;
                }
            }
            if(products.size() == stocks.size())break;
        }
        if(products.size() == stocks.size()){
            for(Stock s : stocks){
                s.setQuantity(s.getQuantity() - orderedQuantity.get(s.getId().getProductId()));
                stockService.update(s);
            }
            Orders order = new Orders(null, null, customer, ordersDTO.getCreatedAt(), ordersDTO.getAddressCountry(), ordersDTO.getAddressCity(), ordersDTO.getAddressCounty(), ordersDTO.getAddressStreetAddress());
            order.setShippedFrom(stocks.get(0).getLocation());
            Orders o = ordersService.insert(order);
            orders.add(order);
            for(Product p : products){
                orderDetailService.insert(new OrderDetail(new OrderDetailID(o.getOrderId(), p.getProductId()), orderedQuantity.get(p.getProductId()), o, p));
            }
        }
        else {
            throw new ProductNotFoundException("A product was not found in stock or database");
        }
        return orders;
    }
}
