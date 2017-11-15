package at.fhv.team05.Application;

import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.IMediumDTO;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class CopyController extends BaseController<Copy, CopyDTO> {
    private static CopyController _theInstance;

    private CopyController() {
        super(Copy.class);
    }

    public static CopyController getInstance() {
        if (_theInstance == null) {
            _theInstance = new CopyController();
        }
        return _theInstance;
    }

    /**
     * Compares the copies by their copy number
     *
     * @param copy
     * @param copyDTO
     * @return returns true if the numbers match
     */
    @Override
    protected boolean compareInput(Copy copy, CopyDTO copyDTO) {
        return copy.getCopyNumber() == copyDTO.getCopyNumber();
    }

    @Override
    protected CopyDTO createDTO(Copy copy) {
        return new CopyDTO(copy);
    }

    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) {

        for (Copy copy : _mapDomainToDto.keySet()) {
            if (copy.getCopyNumber() == copyNumber) {
                return new ResultDTO<>(_mapDomainToDto.get(copy), null);
            }
        }
        return null;
    }

    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) {
        Copy copy = _controllerFacade.getDomainCopy(copyDTO);
        if (copy.getRental() == null) {
            return new ResultDTO<>(false, new Exception("The given copy is not lent."));
        }

        //Set the return Date
        Calendar currenttime = Calendar.getInstance();
        Date currentDate = new Date((currenttime.getTime()).getTime());
        copy.getRental().setReturnDate(currentDate);

        copy.setRentalId(null);
        copy.setCopyStatus("available");
        save(copy);
        //Also force a update of the rental repository. Save of copy does not update the rentals
        RentalController.getInstance().fillMap();

        //Check if a reservation exists
        boolean reservationExists = _controllerFacade.existsReservationForMedium(copy.getMediumId(), copy.getMediaType());
        if (reservationExists) {
            return new ResultDTO<>(true, new Exception("Return process successful. There is a reservation for this media."));
        } else {
            return new ResultDTO<>(true, new Exception("Return process successful."));
        }
    }

    protected ResultListDTO<CopyDTO> getCopiesByMediumID(IMediumDTO mediumDTO) {
        List<CopyDTO> list = new LinkedList<>();
        _mapDomainToDto.keySet().stream().
                filter(i -> i.getMediumId() == mediumDTO.getId() && i.getMediaType().equalsIgnoreCase(mediumDTO.getType())).
                forEach(i -> list.add(_mapDomainToDto.get(i)));
        return new ResultListDTO<>(list, null);
    }
}
