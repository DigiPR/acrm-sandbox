package ch.fhnw.acrm.data.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "partner")
@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "partner_id")
    private List<PartnerAddress> partnerAddresses = new ArrayList<>();

    public List<PartnerAddress> getPartnerAddresses() {
        return partnerAddresses;
    }

    public void setPartnerAddresses(List<PartnerAddress> partnerAddresses) {
        this.partnerAddresses = partnerAddresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}