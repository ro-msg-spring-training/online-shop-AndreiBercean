package ro.msg.learning.shop.dto.builder;

import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;

public class ProductBuilder {
    public static ProductDTO generateDTOFromEntity(Product product){
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getWeight(),
                product.getProductCategory().getCategoryId(),
                product.getProductCategory().getName(),
                product.getProductCategory().getDescription(),
                product.getSupplier().getSupplierId(),
                product.getSupplier().getName(),
                product.getImageURL());
    }

    public static Product generateEntityFromDTO(ProductDTO productDTO){
        return new Product();
    }
}
