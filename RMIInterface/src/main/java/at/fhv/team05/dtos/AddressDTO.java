package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IAddress;

import java.io.Serializable;

public class AddressDTO implements Serializable{

    private int id;
    private String street;
    private String streetNumber;
    private String zip;
    private String city;

    public AddressDTO(IAddress address){
        id=address.getId();
        street=address.getStreet();
        streetNumber=address.getStreetNumber();
        zip=address.getZip();
        city=address.getCity();
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressDTO that = (AddressDTO) o;

        if (id != that.id) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (streetNumber != null ? !streetNumber.equals(that.streetNumber)
                : that.streetNumber != null) {
            return false;
        }
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        return city != null ? city.equals(that.city) : that.city == null;
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
