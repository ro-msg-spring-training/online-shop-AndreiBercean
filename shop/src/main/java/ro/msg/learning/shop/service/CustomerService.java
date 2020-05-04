package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer findById(Customer customer){
        Optional<Customer> customerFound  = customerRepository.findById(customer.getCustomerId());
        return customerFound.orElseGet(Customer::new);
    }

    public Customer findByUsername(String username){
        Optional<Customer> customerFound  = customerRepository.findByUsername(username);
        return customerFound.orElseThrow();
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer delete(Customer customer) {
        customerRepository.deleteById(customer.getCustomerId());
        return customer;
    }
}
