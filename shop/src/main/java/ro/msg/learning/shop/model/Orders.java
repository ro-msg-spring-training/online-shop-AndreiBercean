package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @OneToOne
    @JoinColumn(name="shipped_from", nullable=false)
    private Location shippedFrom;

    @OneToOne
    @JoinColumn(name="customer", nullable=false)
    private Customer customer;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @Column
    private String addressCountry;

    @Column
    private String addressCity;

    @Column
    private String addressCounty;

    @Column(name = "address_streetaddress")
    private String addressStreetAddress;
}
