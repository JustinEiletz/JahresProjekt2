package objects;

import javax.persistence.*;

@Entity
@Table(name = "RENTAL")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", unique = true)
    private final Integer id;

    @Column(name = "ObjectNr")
    private Integer objectNr;

    @Column(name = "ObjectTyp")
    private String objectTyp;

    @Column(name = "ObjectDesc")
    private String objectDesc;

    @Embedded
    @Column(name = "Address")
    private Address address;

    @Column(name = "LivingSpace")
    private Double livingSpace;

    @Column(name = "PriceSquareMeterCold")
    private Double priceSquareMeterCold;

    @Column(name = "AdditionalCosts")
    private Double additionalCosts;

    @Column(name = "Notice")
    private String notice;

    public Rental(final Integer id, final Integer objectNr, final String objectTyp, final String objectDesc, final Address address, final Double livingSpace, final Double priceSquareMeterCold, Double additionalCosts, final String notice) {
        this.id = id;
        this.objectNr = objectNr;
        this.objectTyp = objectTyp;
        this.objectDesc = objectDesc;
        this.address = address;
        this.livingSpace = livingSpace;
        this.priceSquareMeterCold = priceSquareMeterCold;
        this.additionalCosts = additionalCosts;
        this.notice = notice;
    }

    public Integer getId() {
        return id;
    }

    public Integer getObjectNr() {
        return objectNr;
    }

    public void setObjectNr(Integer objectNr) {
        this.objectNr = objectNr;
    }

    public String getObjectTyp() {
        return objectTyp;
    }

    public void setObjectTyp(String objectTyp) {
        this.objectTyp = objectTyp;
    }

    public String getObjectDesc() {
        return objectDesc;
    }

    public void setObjectDesc(String objectDesc) {
        this.objectDesc = objectDesc;
    }

    public Double getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(Double livingSpace) {
        this.livingSpace = livingSpace;
    }

    public Double getPriceSquareMeterCold() {
        return priceSquareMeterCold;
    }

    public void setPriceSquareMeterCold(Double priceSquareMeterCold) {
        this.priceSquareMeterCold = priceSquareMeterCold;
    }

    public Double getAdditionalCosts() {
        return additionalCosts;
    }

    public void setAdditionalCosts(Double additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", objectNr=" + objectNr +
                ", objectTyp='" + objectTyp + '\'' +
                ", objectDesc='" + objectDesc + '\'' +
                ", livingSpace=" + livingSpace +
                ", priceSquareMeterCold=" + priceSquareMeterCold +
                ", additionalCosts=" + additionalCosts +
                ", notice='" + notice + '\'' +
                '}';
    }
}
