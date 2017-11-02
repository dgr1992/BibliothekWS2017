package at.fhv.team05.domain;

import at.fhv.team05.dtos.IRental;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Rental")
public class Rental implements IRental{
    private int id;
    private int copyId;
    private int customerId;
    private Date pickupDate;
    private Date returnDate;
    private Date deadline;
    private int extendCounter;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "copyId", nullable = false)
    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    @Basic
    @Column(name = "customerId", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "pickupDate", nullable = false)
    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    @Basic
    @Column(name = "returnDate", nullable = true)
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Basic
    @Column(name = "deadline", nullable = false)
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "extendCounter", nullable = false)
    public int getExtendCounter() {
        return extendCounter;
    }

    public void setExtendCounter(int extendCounter) {
        this.extendCounter = extendCounter;
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

        if (id != that.id) {
            return false;
        }
        if (copyId != that.copyId) {
            return false;
        }
        if (customerId != that.customerId) {
            return false;
        }
        if (extendCounter != that.extendCounter) {
            return false;
        }
        if (pickupDate != null ? !pickupDate.equals(that.pickupDate) : that.pickupDate != null) {
            return false;
        }
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) {
            return false;
        }
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + copyId;
        result = 31 * result + customerId;
        result = 31 * result + (pickupDate != null ? pickupDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + extendCounter;
        return result;
    }
}
