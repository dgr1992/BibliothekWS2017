package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IRental;

import java.io.Serializable;
import java.sql.Date;

public class RentalDTO implements Serializable {
    private int id;
    private int copyId;
    private int customerId;
    private Date pickupDate;
    private Date returnDate;
    private Date deadline;
    private int extendCounter;

    public RentalDTO(IRental rental) {
        id = rental.getId();
        copyId = rental.getCopyId();
        customerId = rental.getCopyId();
        pickupDate = rental.getPickupDate();
        returnDate = rental.getReturnDate();
        deadline = rental.getDeadline();
        extendCounter = rental.getExtendCounter();
    }


    public int getId() {
        return id;
    }

    public int getCopyId() {
        return copyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public int getExtendCounter() {
        return extendCounter;
    }
}
