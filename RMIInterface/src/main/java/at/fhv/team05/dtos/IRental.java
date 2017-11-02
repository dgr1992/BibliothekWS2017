package at.fhv.team05.dtos;

import java.sql.Date;

public interface IRental {

    int getId();

    int getCopyId();

    int getCustomerId();

    Date getPickupDate();

    Date getReturnDate();

    Date getDeadline();

    int getExtendCounter();


}
