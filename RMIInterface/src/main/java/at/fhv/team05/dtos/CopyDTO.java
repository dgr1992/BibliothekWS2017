package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.ICopy;

import java.io.Serializable;

/**
 * Created by Daniel on 05.11.2017.
 */
public class CopyDTO implements Serializable {

    private int _copyNumber;
    private int _id;
    private String _mediaType;
    private int _mediumId;
    private RentalDTO _rental;
    private CategoryDTO _category;
    private String _copyStatus;

    public CopyDTO(ICopy copy) {
        _copyNumber = copy.getCopyNumber();
        _id = copy.getId();
        _mediaType = copy.getMediaType();
        _mediumId = copy.getMediumId();
        _category = new CategoryDTO(copy.getCategory());
        _copyStatus = copy.getCopyStatus();
        _rental = (copy.getRental() != null ? new RentalDTO(copy.getRental(), this) : null);
    }

    public int getCopyNumber() {
        return _copyNumber;
    }

    public void setCopyNumber(int _copyNumber) {
        this._copyNumber = _copyNumber;
    }

    public int getId() {
        return _id;
    }

    public String getMediaType() {
        return _mediaType;
    }

    public void setMediaType(String _mediaType) {
        this._mediaType = _mediaType;
    }

    public int getMediumId() {
        return _mediumId;
    }

    public void setMediumId(int _mediumId) {
        this._mediumId = _mediumId;
    }


    public RentalDTO getRental() {
        return _rental;
    }

    public void setRental(RentalDTO _rental) {
        this._rental = _rental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CopyDTO copyDTO = (CopyDTO) o;

        if (_copyNumber != copyDTO._copyNumber) {
            return false;
        }
        if (_id != copyDTO._id) {
            return false;
        }
        if (_mediumId != copyDTO._mediumId) {
            return false;
        }
        if (_mediaType != null ? !_mediaType.equals(copyDTO._mediaType)
                : copyDTO._mediaType != null) {
            return false;
        }
//        return _rental != null ? _rental.equals(copyDTO._rental) : copyDTO._rental == null;
        return true;
    }

    @Override
    public int hashCode() {
        int result = _copyNumber;
        result = 31 * result + _id;
        result = 31 * result + (_mediaType != null ? _mediaType.hashCode() : 0);
        result = 31 * result + _mediumId;
//        result = 31 * result + (_rental != null ? _rental.hashCode() : 0);
        return result;
    }

    public CategoryDTO getCategory() {
        return _category;
    }

    public void setCategory(CategoryDTO category) {
        _category = category;
    }

    public String getCopyStatus() {
        return _copyStatus;
    }

    public void setCopyStatus(String copyStatus) {
        _copyStatus = copyStatus;
    }
}
