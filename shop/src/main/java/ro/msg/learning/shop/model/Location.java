package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column
    private String name;

    @Column
    private String addressCountry;

    @Column
    private String addressCity;

    @Column
    private String addressCounty;

    @Column(name = "address_streetaddress")
    private String addressStreetAddress;

}
