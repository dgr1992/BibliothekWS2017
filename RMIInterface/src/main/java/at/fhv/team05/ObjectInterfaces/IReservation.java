package at.fhv.team05.ObjectInterfaces;

import java.util.Date;

public interface IReservation {

    int getId();

    int getMediumId();

    String getMediaType();

    Date getReservationDate();

    ICustomer getCustomer();
}
