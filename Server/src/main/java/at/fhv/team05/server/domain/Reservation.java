package at.fhv.team05.server.domain;

import at.fhv.team05.library.ObjectInterfaces.IReservation;
import at.fhv.team05.server.persistence.DatabaseConnection;
import at.fhv.team05.server.persistence.Repository;
import at.fhv.team05.server.persistence.RepositoryFactory;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Reservation")
public class Reservation implements IDomainObject, IReservation {
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

    @Override
    @Basic
    @Column(name = "mediumId", nullable = false)
    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
        this.mediumId = mediumId;
    }

    @Override
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customerId", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    @Basic
    @Column(name = "mediaType", nullable = false, length = 50)
    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
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

    public static void main(String[] args) {
        DatabaseConnection.init();

        Repository<Reservation> rep = RepositoryFactory.getRepositoryInstance(Reservation.class);

        Reservation res = rep.list().get(0);

        System.out.println("ID: " + res.getId());
        System.out.println("Customer: " + res.getCustomer().getFirstName() + " " + res.getCustomer().getLastName());
        System.out.println("Medium: " + res.getMediumId() + " " + res.getMediaType());
    }
}
