package at.fhv.team05.domain;

import at.fhv.team05.ObjectInterfaces.ICopy;
import at.fhv.team05.ObjectInterfaces.ICustomer;
import at.fhv.team05.ObjectInterfaces.IRental;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Rental")
public class Rental implements IRental{
    @Id
    @Column(name = "id", nullable = false)
    private int _id;

    @Basic
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "copyId")
    private int _copyId;

    @Basic
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer _customer;

    @Basic
    @Column(name = "pickupDate", nullable = false)
    private Date _pickupDate;

    @Basic
    @Column(name = "returnDate", nullable = true)
    private Date _returnDate;

    @Basic
    @Column(name = "deadline", nullable = false)
    private Date _deadline;

    @Basic
    @Column(name = "extendCounter", nullable = false)
    private int _extendCounter;

    public int getId() {
        return _id;
    }

    @Override
    public ICopy getCopy() {
        return null;
    }

    @Override
    public ICustomer getCustomer() {
        return null;
    }

    public void setId(int id) {
        _id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rental that = (Rental) o;

        if (_id != that._id) {
            return false;
        }
        if (_copyId != that._copyId) {
            return false;
        }
        if (_customer != that._customer) {
            return false;
        }
        if (_extendCounter != that._extendCounter) {
            return false;
        }
        if (_pickupDate != null ? !_pickupDate.equals(that._pickupDate) : that._pickupDate != null) {
            return false;
        }
        if (_returnDate != null ? !_returnDate.equals(that._returnDate) : that._returnDate != null) {
            return false;
        }
        if (_deadline != null ? !_deadline.equals(that._deadline) : that._deadline != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + _copyId;
        result = 31 * result + (_customer != null ? _customer.hashCode() : 0);
        result = 31 * result + (_pickupDate != null ? _pickupDate.hashCode() : 0);
        result = 31 * result + (_returnDate != null ? _returnDate.hashCode() : 0);
        result = 31 * result + (_deadline != null ? _deadline.hashCode() : 0);
        result = 31 * result + _extendCounter;
        return result;
    }
}
