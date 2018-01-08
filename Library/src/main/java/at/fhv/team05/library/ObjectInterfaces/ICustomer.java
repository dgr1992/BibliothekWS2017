package at.fhv.team05.library.ObjectInterfaces;

import java.sql.Date;

public interface ICustomer {

    int getId();
    String getFirstName();
    String getLastName();
    int getCustomerId();
    IAddress getAddress();
    Date getDateOfBirth();
    String getEmail();
    String getPhoneNumber();
    Date getPaymentDate();

}
