package at.fhv.team05.ObjectInterfaces;

public interface IRental {

    int getId();

    ICopy getCopy();

    ICustomer getCustomer();

    java.util.Date getPickupDate();

    java.util.Date getReturnDate();

    java.util.Date getDeadline();

    int getExtendCounter();


}
