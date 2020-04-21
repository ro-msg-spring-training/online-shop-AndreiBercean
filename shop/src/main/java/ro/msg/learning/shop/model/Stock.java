package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Stock implements Serializable {

    @EmbeddedId
    private StockID id;

    @Column
    private Integer quantity;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product")
    private Product product;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name="location")
    private Location location;
}
