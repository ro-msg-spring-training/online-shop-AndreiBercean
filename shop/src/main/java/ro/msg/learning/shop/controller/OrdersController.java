package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDetailDTO;
import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.*;
import ro.msg.learning.shop.service.util.SearchStrategy;
import ro.msg.learning.shop.service.util.StrategyFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrdersController {
    private final StrategyFactory strategyFactory;

    @PostMapping()
    @Transactional
    public ResponseEntity<List<Orders>> insert(@RequestBody OrdersDTO ordersDTO) {
        SearchStrategy strategy = strategyFactory.getStrategy();
        try {
            List<Orders> orders;
            orders = strategy.insert(ordersDTO);
            return ResponseEntity.ok(orders);
        } catch (ProductNotFoundException | InsufficientStockException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        }
    }
}
