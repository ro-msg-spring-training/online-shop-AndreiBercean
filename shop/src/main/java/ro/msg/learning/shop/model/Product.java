package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private Double weight;

    @ManyToOne
    @JoinColumn(name="product_category", nullable=false)
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name="supplier", nullable=false)
    private Supplier supplier;

    @Column(name = "image_url")
    private String imageURL;
}
