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
}
