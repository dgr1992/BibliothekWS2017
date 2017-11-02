package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IReservation;

import java.io.Serializable;
import java.util.Date;

public class ReservationDTO implements Serializable {
    private int id;
    private int mediumId;
    private int customerId;
    private String mediaType;
    private Date reservationDate;

    public ReservationDTO(IReservation reservation) {
        id = reservation.getId();
        mediumId = reservation.getMediumId();
        customerId = reservation.getCustomerId();
        mediaType = reservation.getMediaType();
        reservationDate = reservation.getReservationDate();
    }

    public int getId() {
        return id;
    }

    public int getMediumId() {
        return mediumId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

}
