package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "streetNumber")
    private String streetNumber;

    @Column(name = "place")
    private String place;

    @Column(name = "postCode")
    private String postCode;

    @OneToMany(targetEntity = Tenant.class, fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private Set<Tenant> tenants;

    @OneToMany(targetEntity = Rental.class, fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private Set<Rental> rentals;

    public Address() {}
    public Address(final String street, final String streetNumber, final String place, final String postCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.place = place;
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(final String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(final String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(final String place) {
        this.place = place;
    }

    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(final String postCode) {
        this.postCode = postCode;
    }

    public Set<Tenant> getTenants() { return tenants; }
    public void setTenants(final Set<Tenant> tenants) { this.tenants = tenants; }

    public Set<Rental> getRentals() { return rentals; }
    public void setRentals(final Set<Rental> rentals) { this.rentals = rentals; }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", place='" + place + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
