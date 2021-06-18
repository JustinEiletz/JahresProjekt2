package objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String zipCode;
    private String place;
    private String postcode;
    private Boolean isPerson;

    public Address() {}

    public Address(final String street, final String zipCode, final String place, final String postcode, final Boolean isPerson) {
        this.street = street;
        this.zipCode = zipCode;
        this.place = place;
        this.postcode = postcode;
        this.isPerson = isPerson;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Boolean getPerson() {
        return isPerson;
    }

    public void setPerson(Boolean person) {
        isPerson = person;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", place='" + place + '\'' +
                ", postcode='" + postcode + '\'' +
                ", isPerson=" + isPerson +
                '}';
    }
}
