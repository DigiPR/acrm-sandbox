package ch.fhnw.acrm.data.domain;

import javax.persistence.*;

@Table(name = "partner_address")
@Entity
public class PartnerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "place", nullable = false)
    private String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}