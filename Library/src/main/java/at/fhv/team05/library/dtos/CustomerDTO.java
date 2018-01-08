package at.fhv.team05.library.dtos;

import at.fhv.team05.library.ObjectInterfaces.ICustomer;

import java.io.Serializable;
import java.sql.Date;

public class CustomerDTO implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int customerId;
    private AddressDTO address;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private Date paymentDate;

    public CustomerDTO(ICustomer customer) {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        customerId = customer.getCustomerId();
        address = new AddressDTO(customer.getAddress());
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

    public AddressDTO getAddress() {
        return address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDTO that = (CustomerDTO) o;

        if (id != that.id) return false;
        if (customerId != that.customerId) return false;
        if (address.getId() != that.address.getId()) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null)
            return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth)
                : that.dateOfBirth != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber)
                : that.phoneNumber != null) {
            return false;
        }
        return paymentDate != null ? paymentDate.equals(that.paymentDate)
                : that.paymentDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + customerId;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        return result;
    }
}
