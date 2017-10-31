package at.fhv.team05.domain;

import javax.persistence.*;

@Entity
@Table(name = "Copy")
public class Copy {
    private int id;
    private int mediumId;
    private int copyNumber;
    private String mediaType;
    private int rentalId;

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

    @Basic
    @Column(name = "copyNumber", nullable = false)
    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
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
    @Column(name = "rentalId", nullable = false)
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
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

        if (id != that.id) {
            return false;
        }
        if (mediumId != that.mediumId) {
            return false;
        }
        if (copyNumber != that.copyNumber) {
            return false;
        }
        if (rentalId != that.rentalId) {
            return false;
        }
        if (mediaType != null ? !mediaType.equals(that.mediaType) : that.mediaType != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mediumId;
        result = 31 * result + copyNumber;
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + rentalId;
        return result;
    }
}
