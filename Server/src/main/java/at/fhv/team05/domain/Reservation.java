package at.fhv.team05.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Reservation")
public class Reservation implements IDomainObject {
    private int id;
    private int mediumId;
    private Customer customer;
    private String mediaType;
    private Date reservationDate;

    @Override
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mediumId", nullable = false)
    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
        this.mediumId = mediumId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Basic
    @Column(name = "mediaType", nullable = false, length = 50)
    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Basic
    @Column(name = "reservationDate", nullable = false)
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reservation that = (Reservation) o;

        if (id != that.id) {
            return false;
        }
        if (mediumId != that.mediumId) {
            return false;
        }
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) {
            return false;
        }
        if (mediaType != null ? !mediaType.equals(that.mediaType) : that.mediaType != null) {
            return false;
        }
        return reservationDate != null ? reservationDate.equals(that.reservationDate) : that.reservationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mediumId;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + (reservationDate != null ? reservationDate.hashCode() : 0);
        return result;
    }
}
