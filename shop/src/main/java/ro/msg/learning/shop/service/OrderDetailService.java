package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.repository.OrderDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetail findById(OrderDetail orderDetail){
        Optional<OrderDetail> detailFound  = orderDetailRepository.findById(orderDetail.getId());
        return detailFound.orElseGet(OrderDetail::new);
    }

    public List<OrderDetail> findAll(){
        return orderDetailRepository.findAll();
    }

    public OrderDetail insert(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> insertAll(List<OrderDetail> list) {
        return orderDetailRepository.saveAll(list);
    }

    public OrderDetail update(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail delete(OrderDetail orderDetail) {
        orderDetailRepository.deleteById(orderDetail.getId());
        return orderDetail;
    }
}
