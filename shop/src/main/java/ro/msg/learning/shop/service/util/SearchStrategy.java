package ro.msg.learning.shop.service.util;

import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import java.util.HashMap;
import java.util.List;

public interface SearchStrategy {
    HashMap<Product, Stock> execute(List<Product> products, HashMap<Product, Integer> orderedQuantity);
}
