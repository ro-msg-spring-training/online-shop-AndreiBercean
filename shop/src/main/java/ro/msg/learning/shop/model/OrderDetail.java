package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    @EmbeddedId
    private OrderDetailID id;

    @Column
    private Integer quantity;

    @ManyToOne
    @MapsId("ordersId")
    @JoinColumn(name="orders", nullable=false)
    private Orders orders;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product", nullable=false)
    private Product product;


}
