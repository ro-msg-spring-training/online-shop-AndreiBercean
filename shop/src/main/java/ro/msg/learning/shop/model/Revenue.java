package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer revenueId;

    @ManyToOne
    @JoinColumn(name="location", nullable=false)
    private Location location;

    @Column
    private Date date;

    @Column
    private BigDecimal sum;


}
