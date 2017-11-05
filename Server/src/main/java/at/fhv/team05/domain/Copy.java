package at.fhv.team05.domain;

import javax.persistence.*;

import at.fhv.team05.ObjectInterfaces.ICopy;

@Entity
@Table(name = "Copy")
public class Copy implements ICopy{
    @Id
    @Column(name = "id", nullable = false)
    private int _id;

    @Basic
    @Column(name = "mediumId", nullable = false)
    private int _mediumId;

    @Basic
    @Column(name = "copyNumber", nullable = false)
    private int _copyNumber;

    @Basic
    @Column(name = "mediaType", nullable = false, length = 50)
    private String _mediaType;

    @Basic
    @Column(name = "rentalId", nullable = true)
    private Rental _rental;


    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }


    public int getMediumId() {
        return _mediumId;
    }

    public void setMediumId(int mediumId) {
        _mediumId = mediumId;
    }


    public int getCopyNumber() {
        return _copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        _copyNumber = copyNumber;
    }


    public String getMediaType() {
        return _mediaType;
    }

    public void setMediaType(String mediaType) {
        _mediaType = mediaType;
    }


    public Rental getRental() {
        return _rental;
    }

    public void setRentalId(Rental rental) {
        _rental = rental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Copy that = (Copy) o;

        if (_id != that._id) {
            return false;
        }
        if (_mediumId != that._mediumId) {
            return false;
        }
        if (_copyNumber != that._copyNumber) {
            return false;
        }
        if (_rental != that._rental) {
            return false;
        }
        if (_mediaType != null ? !_mediaType.equals(that._mediaType) : that._mediaType != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + _mediumId;
        result = 31 * result + _copyNumber;
        result = 31 * result + (_mediaType != null ? _mediaType.hashCode() : 0);
        result = 31 * result + (_rental != null ? _rental.hashCode() : 0);
        return result;
    }
}
