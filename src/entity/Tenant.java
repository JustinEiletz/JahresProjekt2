package entity;

import javax.persistence.*;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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
