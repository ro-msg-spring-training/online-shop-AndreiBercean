package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Integer id) throws ProductNotFoundException {
        Optional<Product> product  = productRepository.findById(id);
        return product.orElseThrow(()-> new ProductNotFoundException("no product was found in the database"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product insert(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public Integer delete(Product product) {
        productRepository.deleteById(product.getProductId());
        return product.getProductId();
    }
}
