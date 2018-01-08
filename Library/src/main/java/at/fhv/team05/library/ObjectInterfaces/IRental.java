package at.fhv.team05.library.ObjectInterfaces;

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
