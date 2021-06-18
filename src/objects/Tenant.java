package objects;

import javax.persistence.*;

@Entity
@Table(name = "TENANT")
@NamedQueries({
        @NamedQuery(
                name = "Tenant.findById",
                query = "SELECT T FROM Tenant WHERE T.id = :Id"
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

    @Column(name = "phonenumber")
    private String phonenumber;

    @Embedded
    @Column(name = "address")
    private Address contact_address;

    public Tenant() {}

    public Tenant(final String name, final String surnme, final String phonenumber, final Address contact_address) {
        this.name = name;
        this.surname = surnme;
        this.phonenumber = phonenumber;
        this.contact_address = contact_address;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Address getContact_address() {
        return contact_address;
    }

    public void setContact_address(Address contact_address) {
        this.contact_address = contact_address;
    }

    @Override
    public String toString() {
        return "Tenannt{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", contact_address='" + contact_address +
                '}';
    }
}
