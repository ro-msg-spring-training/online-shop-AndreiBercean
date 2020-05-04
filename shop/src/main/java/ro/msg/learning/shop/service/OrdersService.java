package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Orders;
import ro.msg.learning.shop.repository.OrdersRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public Orders findById(Orders orders){
        Optional<Orders> ordersFound  = ordersRepository.findById(orders.getOrderId());
        return ordersFound.orElseGet(Orders::new);
    }

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public Orders insert(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Orders update(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Orders delete(Orders orders) {
        ordersRepository.deleteById(orders.getOrderId());
        return orders;
    }

}
