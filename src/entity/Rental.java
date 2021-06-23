package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RENTAL")
@NamedQueries({
        @NamedQuery(
                name = "Rental.findById",
                query = "SELECT R FROM Rental R WHERE R.id = :Id"
        ),
        @NamedQuery(
                name = "Rental.findAll",
                query = "SELECT R FROM Rental R"
        ),
})
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "objectNr")
    private Integer objectNr;

    @Column(name = "objectTyp")
    private String objectTyp;

    @Column(name = "objectDesc")
    private String objectDesc;

    @Embedded
    @Column(name = "address")
    private Address address;

    @Column(name = "livingSpace")
    private Double livingSpace;

    @Column(name = "priceSquareMeterCold")
    private Double priceSquareMeterCold;

    @Column(name = "additionalCosts")
    private Double additionalCosts;

    @Column(name = "notice")
    private String notice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "TENANT_RENTALS",
            joinColumns = {@JoinColumn(name = "rentalId", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "tenantId", referencedColumnName = "id")
            })
    private Tenant tenant;

    public Rental() {}
    public Rental(final Integer objectNr, final String objectTyp, final String objectDesc, final Address address, final Double livingSpace, final Double priceSquareMeterCold, final Double additionalCosts, final String notice) {
        this.objectNr = objectNr;
        this.objectTyp = objectTyp;
        this.objectDesc = objectDesc;
        this.address = address;
        this.livingSpace = livingSpace;
        this.priceSquareMeterCold = priceSquareMeterCold;
        this.additionalCosts = additionalCosts;
        this.notice = notice;
    }
    public Integer getId() { return id; }

    public Integer getObjectNr() { return objectNr; }
    public void setObjectNr(final Integer objectNr) { this.objectNr = objectNr; }

    public String getObjectTyp() { return objectTyp; }
    public void setObjectTyp(final String objectTyp) { this.objectTyp = objectTyp; }

    public String getObjectDesc() { return objectDesc; }
    public void setObjectDesc(final String objectDesc) { this.objectDesc = objectDesc; }

    public Address getAddress() { return address; }
    public void setAddress(final Address address) { this.address = address; }

    public Double getLivingSpace() { return livingSpace; }
    public void setLivingSpace(final Double livingSpace) { this.livingSpace = livingSpace; }

    public Double getPriceSquareMeterCold() { return priceSquareMeterCold; }
    public void setPriceSquareMeterCold(final Double priceSquareMeterCold) { this.priceSquareMeterCold = priceSquareMeterCold; }

    public Double getAdditionalCosts() { return additionalCosts; }
    public void setAdditionalCosts(final Double additionalCosts) { this.additionalCosts = additionalCosts; }

    public String getNotice() { return notice; }
    public void setNotice(final String notice) { this.notice = notice; }

    public Tenant getTenant() { return tenant; }
    public void setTenant(final Tenant tenant) { this.tenant = tenant; }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", objectNr=" + objectNr +
                ", objectTyp='" + objectTyp + '\'' +
                ", objectDesc='" + objectDesc + '\'' +
                ", address=" + address +
                ", livingSpace=" + livingSpace +
                ", priceSquareMeterCold=" + priceSquareMeterCold +
                ", additionalCosts=" + additionalCosts +
                ", notice='" + notice + '\'' +
                '}';
    }
}
