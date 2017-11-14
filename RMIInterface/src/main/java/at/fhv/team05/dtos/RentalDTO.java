package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IRental;

import java.io.Serializable;
import java.util.Date;

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

    public RentalDTO(CopyDTO copy, CustomerDTO customer, Date pickupDate, Date deadline) {
        _copy = copy;
        _customer = customer;
        _pickupDate = pickupDate;
        _deadline = deadline;
        _extendCounter = 0;
    }

    public RentalDTO() {
        _extendCounter = 0;
    }

    public RentalDTO(IRental rental, CopyDTO copyDTO) {
        _id = rental.getId();
        _copy = copyDTO;
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

    public void setCopy(CopyDTO copy) {
        _copy = copy;
    }

    public CustomerDTO getCustomer() {
        return _customer;
    }

    public void setCustomer(CustomerDTO customer) {
        _customer = customer;
    }

    public Date getPickupDate() {
        return _pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        _pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return _returnDate;
    }

    public void setReturnDate(Date returnDate) {
        _returnDate = returnDate;
    }

    public Date getDeadline() {
        return _deadline;
    }

    public void setDeadline(Date deadline) {
        _deadline = deadline;
    }

    public int getExtendCounter() {
        return _extendCounter;
    }

    public void setExtendCounter(int extendCounter) {
        _extendCounter = extendCounter;
    }

    public void incrementExtendCounter() {
        _extendCounter++;
    }
}
