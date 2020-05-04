package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockID implements Serializable {

    @Column(name = "product",nullable = false)
    private int productId;

    @Column(name = "location",nullable = false)
    private int locationId;

}
