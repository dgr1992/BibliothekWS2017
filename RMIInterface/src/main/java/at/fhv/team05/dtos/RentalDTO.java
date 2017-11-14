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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RentalDTO rentalDTO = (RentalDTO) o;

        if (_id != rentalDTO._id) {
            return false;
        }
        if (_extendCounter != rentalDTO._extendCounter) {
            return false;
        }
//        if (_copy != null ? !_copy.equals(rentalDTO._copy) : rentalDTO._copy != null) {
//            return false;
//        }
        if (_customer != null ? !_customer.equals(rentalDTO._customer) : rentalDTO._customer != null) {
            return false;
        }
        if (_pickupDate != null ? !_pickupDate.equals(rentalDTO._pickupDate) : rentalDTO._pickupDate != null) {
            return false;
        }
        if (_returnDate != null ? !_returnDate.equals(rentalDTO._returnDate) : rentalDTO._returnDate != null) {
            return false;
        }
        return _deadline != null ? _deadline.equals(rentalDTO._deadline) : rentalDTO._deadline == null;
    }

    @Override
    public int hashCode() {
        int result = _id;
//        result = 31 * result + (_copy != null ? _copy.hashCode() : 0);
        result = 31 * result + (_customer != null ? _customer.hashCode() : 0);
        result = 31 * result + (_pickupDate != null ? _pickupDate.hashCode() : 0);
        result = 31 * result + (_returnDate != null ? _returnDate.hashCode() : 0);
        result = 31 * result + (_deadline != null ? _deadline.hashCode() : 0);
        result = 31 * result + _extendCounter;
        return result;
    }
}
