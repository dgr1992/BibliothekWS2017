package at.fhv.team05.ObjectInterfaces;

import java.sql.Date;

public interface IRental {

    int getId();

    ICopy getCopy();

    ICustomer getCustomer();

    Date getPickupDate();

    Date getReturnDate();

    Date getDeadline();

    int getExtendCounter();


}
