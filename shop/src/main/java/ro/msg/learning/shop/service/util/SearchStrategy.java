package ro.msg.learning.shop.service.util;

import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.Orders;

import java.util.List;

public interface SearchStrategy {
    List<Orders> insert(OrdersDTO ordersDTO) throws ProductNotFoundException, InsufficientStockException;
}
