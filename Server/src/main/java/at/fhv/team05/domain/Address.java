package at.fhv.team05.domain;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address implements IDomainObject {
    private int id;
    private String street;
    private String streetNumber;
    private String zip;
    private String city;

    @Override
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "street", nullable = false, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "streetNumber", nullable = false, length = 10)
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Basic
    @Column(name = "zip", nullable = false, length = 20)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address that = (Address) o;

        if (id != that.id) {
            return false;
        }
        if (street != null ? !street.equals(that.street) : that.street != null) {
            return false;
        }
        if (streetNumber != null ? !streetNumber.equals(that.streetNumber) : that.streetNumber != null) {
            return false;
        }
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) {
            return false;
        }
        if (city != null ? !city.equals(that.city) : that.city != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
