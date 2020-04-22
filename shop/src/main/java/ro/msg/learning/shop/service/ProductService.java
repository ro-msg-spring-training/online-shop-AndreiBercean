package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.dto.builder.ProductBuilder;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO findById(Integer id){
        Optional<Product> product  = productRepository.findById(id);
        return ProductBuilder.generateDTOFromEntity(product.get());
    }

    public List<ProductDTO> findAll(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public Integer insert(ProductDTO productDTO) {
        return productRepository.save(ProductBuilder.generateEntityFromDTO(productDTO)).getProductId();
    }

    public Integer update(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(productDTO.getProductId());

        if(product.isEmpty()){
            return -1;
        }
        return productRepository.save(ProductBuilder.generateEntityFromDTO(productDTO)).getProductId();
    }

    public Integer delete(ProductDTO productDTO) {
        productRepository.deleteById(productDTO.getProductId());
        return 1;
    }
}
