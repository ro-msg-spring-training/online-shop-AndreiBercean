package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Revenue;
import ro.msg.learning.shop.repository.RevenueRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevenueService {
    private final RevenueRepository revenueRepository;

    public Revenue findById(Revenue revenue){
        Optional<Revenue> revenueFound  = revenueRepository.findById(revenue.getRevenueId());
        return revenueFound.orElseGet(Revenue::new);
    }

    public List<Revenue> findAll(){
        return revenueRepository.findAll();
    }

    public Revenue insert(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    public Revenue update(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    public Revenue delete(Revenue revenue) {
        revenueRepository.deleteById(revenue.getRevenueId());
        return revenue;
    }
}
