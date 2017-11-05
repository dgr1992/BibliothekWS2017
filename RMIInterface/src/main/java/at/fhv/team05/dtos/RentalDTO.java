package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IRental;

import java.io.Serializable;
import java.sql.Date;

public class RentalDTO implements Serializable {
    private int _id;
    private CopyDTO _copy;
    private CustomerDTO _customer;
    private Date _pickupDate;
    private Date _returnDate;
    private Date _deadline;
    private int _extendCounter;

    public RentalDTO(IRental rental) {
        _id = rental.getId();
        _copy = new CopyDTO(rental.getCopy());
        _customer = new CustomerDTO(rental.getCustomer());
        _pickupDate = rental.getPickupDate();
        _returnDate = rental.getReturnDate();
        _deadline = rental.getDeadline();
        _extendCounter = rental.getExtendCounter();
    }


    public int getId() {
        return _id;
    }

    public CopyDTO getCopy() {
        return _copy;
    }

    public CustomerDTO getCustomer() { return _customer; }

    public Date getPickupDate() {
        return _pickupDate;
    }

    public Date getReturnDate() {
        return _returnDate;
    }

    public Date getDeadline() {
        return _deadline;
    }

    public int getExtendCounter() {
        return _extendCounter;
    }
}
