package ro.msg.learning.shop.dto.converter;

import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

public class ProductConverter {
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
        return new Product(
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getWeight(),
                new ProductCategory(
                        productDTO.getCategoryId(),
                        productDTO.getName(),
                        productDTO.getDescription()
                ),
                new Supplier(
                        productDTO.getSupplierId(),
                        productDTO.getName()
                ),
                productDTO.getImageURL()
        );
    }
}
