package ro.msg.learning.shop.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDetailDTO;
import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MostAbundantStrategy implements SearchStrategy {
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

    private Orders getPrevious(List<Orders> list, Orders order){
        for(Orders item : list){
            if(item.getShippedFrom().equals(order.getShippedFrom()))return item;
        }
        return new Orders();
    }

    @Override
    public List<Orders> insert(OrdersDTO ordersDTO) throws ProductNotFoundException, InsufficientStockException {
        Customer customer = new Customer(2, "Andrei", "Bercean", "AndreiBercean", "Password", "andreibercean@yahoo.com");
        List<Orders> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<OrderDetailDTO> orderDetailDTOS = ordersDTO.getProducts();
        HashMap<Integer, Integer> orderedQuantity = new HashMap<>();
        for(OrderDetailDTO o : orderDetailDTOS){
            orderedQuantity.put(o.getProductId(), o.getQuantity());
            if(productService.findById(o.getProductId())!=null)
                products.add(productService.findById(o.getProductId()));
        }
        List<Stock> stocks = new ArrayList<>();
        for(Product product : products){
            Stock max = new Stock();
            max.setQuantity(0);
            for(Stock stock : stockService.findByProduct(product)){
                if(stock.getQuantity() >= max.getQuantity())max = stock;
            }
            if(max.getQuantity() > orderedQuantity.get(product.getProductId())){
                stocks.add(max);
            }
            else {
                throw new InsufficientStockException(max.getProduct().getName() + " was not in stock");
            }
        }

        if(products.size() == stocks.size()){
            for(Stock s : stocks){
                s.setQuantity(s.getQuantity() - orderedQuantity.get(s.getId().getProductId()));
                stockService.update(s);
                Orders order = new Orders(null, null, customer, ordersDTO.getCreatedAt(), ordersDTO.getAddressCountry(), ordersDTO.getAddressCity(), ordersDTO.getAddressCounty(), ordersDTO.getAddressStreetAddress());
                order.setShippedFrom(s.getLocation());
                if(!contains(orders, order)){
                    Orders o = ordersService.insert(order);
                    orders.add(o);
                    orderDetailService.insert(new OrderDetail(new OrderDetailID(o.getOrderId(), s.getProduct().getProductId()), orderedQuantity.get(s.getId().getProductId()), o, s.getProduct()));
                }
                else{
                    Orders o = getPrevious(orders, order);
                    orderDetailService.insert(new OrderDetail(new OrderDetailID(o.getOrderId(), s.getProduct().getProductId()), orderedQuantity.get(s.getId().getProductId()), o, s.getProduct()));
                }
            }
        }
        return orders;
    }
}
