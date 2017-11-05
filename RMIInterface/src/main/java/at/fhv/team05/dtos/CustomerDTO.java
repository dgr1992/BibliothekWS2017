package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.ICustomer;

import java.io.Serializable;
import java.util.Date;

public class CustomerDTO implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int customerId;
    private int addressId;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private Date paymentDate;

    public CustomerDTO(ICustomer customer) {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        customerId = customer.getCustomerID();
        addressId = customer.getAddressId();
        dateOfBirth = customer.getDateOfBirth();
        email = customer.getEmail();
        phoneNumber = customer.getPhoneNumber();
        paymentDate = customer.getPaymentDate();
    }

    public CustomerDTO(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
}
