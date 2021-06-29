package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TENANT")
@NamedQueries({
        @NamedQuery(
                name = "Tenant.findById",
                query = "SELECT T FROM Tenant T WHERE T.id = :Id"
        ),
        @NamedQuery(
                name = "Tenant.findAll",
                query = "SELECT T FROM Tenant T"
        ),
})
public class Tenant {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "forename")
    private String forename;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(targetEntity = Rental.class, fetch = FetchType.LAZY, mappedBy = "tenant", cascade = CascadeType.ALL)
    private Set<Rental> rentals;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "TENANT_ADDRESS",
            joinColumns = {@JoinColumn(name = "addressId", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "tenantId", referencedColumnName = "id")
            })
    private Address address;

    public Tenant() {}
    public Tenant(final String forename, final String surname, final String phoneNumber) {
        this.forename = forename;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getForeName() { return forename; }
    public void setForeName(final String forename) { this.forename = forename; }

    public String getSurName() {
        return surname;
    }
    public void setSurName(final String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() { return address; }
    public void setAddress(final Address address) { this.address = address; }

    public Set<Rental> getRentals() { return rentals; }
    public void setRentals(final Set<Rental> rentals) { this.rentals = rentals; }
}
