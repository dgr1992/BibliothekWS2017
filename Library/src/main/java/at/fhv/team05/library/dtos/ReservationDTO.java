package at.fhv.team05.library.dtos;

import at.fhv.team05.library.ObjectInterfaces.IReservation;

import java.io.Serializable;
import java.util.Date;

public class ReservationDTO implements Serializable {
    private int id;
    private int mediumId;
    private CustomerDTO customer;
    private String mediaType;
    private Date reservationDate;

    public ReservationDTO(IReservation reservation) {
        id = reservation.getId();
        mediumId = reservation.getMediumId();
        customer = new CustomerDTO(reservation.getCustomer());
        mediaType = reservation.getMediaType();
        reservationDate = reservation.getReservationDate();
    }

    public int getId() {
        return id;
    }

    public int getMediumId() {
        return mediumId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

}
