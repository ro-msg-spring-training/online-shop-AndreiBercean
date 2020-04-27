package ro.msg.learning.shop.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
    private Integer supplierId;
    private String supplierName;
    private String imageURL;

}
