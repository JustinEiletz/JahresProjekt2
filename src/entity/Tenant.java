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
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Embedded
    @Column(name = "address")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Rental rental;

    public Tenant() {}
    public Tenant(final String name, final String surname, final String phoneNumber, final Address address) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() { return address; }
    public void setAddress(final Address address) {
        this.address = address;
    }

    public Rental getRental() { return rental; }
    public void setRentals(final Rental rental) { this.rental = rental; }

    @Override
    public String toString() {
        return "Tenannt{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address +
                '}';
    }
}
