package at.fhv.team05.domain;

import at.fhv.team05.ObjectInterfaces.ICopy;
import at.fhv.team05.ObjectInterfaces.ICustomer;
import at.fhv.team05.ObjectInterfaces.IRental;
import at.fhv.team05.persistence.DBFacade;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Rental")
public class Rental implements IRental, IDomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int _id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "copyId")
    private Copy _copy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer _customer;

    @Column(name = "pickupDate", nullable = false)
    private Date _pickupDate;


    @Column(name = "returnDate", nullable = true)
    private Date _returnDate;


    @Column(name = "deadline", nullable = false)
    private Date _deadline;


    @Column(name = "extendCounter", nullable = false)
    private int _extendCounter;

    @Override
    public int getId() {
        return _id;
    }

    @Override
    public ICopy getCopy() {
        return _copy;
    }

    public void setCopy(Copy copy) {
        _copy = copy;
    }

    @Override
    public ICustomer getCustomer() {
        return _customer;
    }

    public void setCustomer(Customer customer) {
        _customer = customer;
    }

    public void setId(int id) {
        _id = id;
    }


    @Override
    public Date getPickupDate() {
        return _pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        _pickupDate = pickupDate;
    }


    @Override
    public Date getReturnDate() {
        return _returnDate;
    }

    public void setReturnDate(Date returnDate) {
        _returnDate = returnDate;
    }


    @Override
    public Date getDeadline() {
        return _deadline;
    }

    public void setDeadline(Date deadline) {
        _deadline = deadline;
    }

    @Override
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

        if (_copy != null ? !_copy.equals(that._copy) : that._copy != null) {
            return false;
        }
        if (_customer != null ? !_customer.equals(that._customer) : that._customer != null) {
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
        result = 31 * result + (_copy != null ? _copy.hashCode() : 0);
        result = 31 * result + (_customer != null ? _customer.hashCode() : 0);
        result = 31 * result + (_pickupDate != null ? _pickupDate.hashCode() : 0);
        result = 31 * result + (_returnDate != null ? _returnDate.hashCode() : 0);
        result = 31 * result + (_deadline != null ? _deadline.hashCode() : 0);
        result = 31 * result + _extendCounter;
        return result;
    }

    public static void main(String[] args) {
        DBFacade dbFacade = DBFacade.getInstance();

        List<Rental> rentals = dbFacade.getAllRental();

        System.out.println(rentals);
    }
}
