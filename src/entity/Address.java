package entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String zipCode;
    private String place;
    private String postcode;
    private boolean isPerson;

    public Address() {}
    public Address(final String street, final String zipCode, final String place, final String postcode, final boolean isPerson) {
        this.street = street;
        this.zipCode = zipCode;
        this.place = place;
        this.postcode = postcode;
        this.isPerson = isPerson;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(final String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(final String place) {
        this.place = place;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public boolean getPerson() {
        return isPerson;
    }
    public void setPerson(final boolean isPerson) {
        this.isPerson = isPerson;
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
