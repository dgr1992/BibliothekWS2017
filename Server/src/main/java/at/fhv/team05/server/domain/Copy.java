package at.fhv.team05.server.domain;

import at.fhv.team05.library.ObjectInterfaces.ICopy;
import at.fhv.team05.server.persistence.RepositoryFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Copy")
public class Copy implements ICopy, IDomainObject, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int _id;

    @Column(name = "mediumId", nullable = false)
    private int _mediumId;

    @Column(name = "copyNumber", nullable = false)
    private int _copyNumber;

    @Column(name = "mediaType", nullable = false, length = 50)
    private String _mediaType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rentalId", nullable = true)
    private Rental _rental;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category _category;

    @Column(name = "copyStatus", nullable = false)
    private String _copyStatus;


    @Override
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }


    @Override
    public int getMediumId() {
        return _mediumId;
    }

    public void setMediumId(int mediumId) {
        _mediumId = mediumId;
    }


    @Override
    public int getCopyNumber() {
        return _copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        _copyNumber = copyNumber;
    }


    @Override
    public String getMediaType() {
        return _mediaType;
    }

    public void setMediaType(String mediaType) {
        _mediaType = mediaType;
    }


    @Override
    public Category getCategory() {
        return _category;
    }

    public void setCategory(Category category) {
        _category = category;
    }

    @Override
    public String getCopyStatus() {
        return _copyStatus;
    }

    public void setCopyStatus(String copyStatus) {
        _copyStatus = copyStatus;
    }


    @Override
    public Rental getRental() {
        return _rental;
    }

    public void setRental(Rental rental) {
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
        return result;
    }

    public static void main(String[] args) {
        List<Copy> copies = RepositoryFactory.getRepositoryInstance(Copy.class).list();

        System.out.println(copies);
    }
}
