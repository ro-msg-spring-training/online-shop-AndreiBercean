package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory findById(ProductCategory category){
        Optional<ProductCategory> categoryFound  = productCategoryRepository.findById(category.getCategoryId());
        return categoryFound.orElseGet(ProductCategory::new);
    }

    public List<ProductCategory> findAll(){
        return productCategoryRepository.findAll();
    }

    public ProductCategory insert(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    public ProductCategory update(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    public ProductCategory delete(ProductCategory category) {
        productCategoryRepository.deleteById(category.getCategoryId());
        return category;
    }
}
