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

    public CopyDTO(ICopy copy) {
        _copyNumber = copy.getCopyNumber();
        _id = copy.getId();
        _mediaType = copy.getMediaType();
        _mediumId = copy.getMediumId();
        _rental = (copy.getRental() != null ? new RentalDTO(copy.getRental()) : null);
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
}
