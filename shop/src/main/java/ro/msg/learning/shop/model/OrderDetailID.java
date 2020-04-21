package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDetailID implements Serializable {

    @Column(name = "orders",nullable = false)
    private int ordersId;

    @Column(name = "product",nullable = false)
    private int productId;

}
