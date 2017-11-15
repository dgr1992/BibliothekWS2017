package at.fhv.team05.Application;

import at.fhv.team05.Enum.ReturnCopyResult;
import at.fhv.team05.ResultDTO;
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

    public ReturnCopyResult returnCopy(CopyDTO copyDTO) {
        Copy copy = _controllerFacade.getDomainCopy(copyDTO);
        if (copy.getRental() == null) {
            return ReturnCopyResult.NotLent;
        }

        //Set the return Date
        Calendar currenttime = Calendar.getInstance();
        Date currentDate = new Date((currenttime.getTime()).getTime());
        copy.getRental().setReturnDate(currentDate);

        copy.setRentalId(null);
        copy.setCopyStatus("available");
        save(copy);

        //Check if a reservation exists
        boolean reservationExists = _controllerFacade.existsReservationForMedium(copy.getMediumId(), copy.getMediaType());
        if (reservationExists) {
            return ReturnCopyResult.ReservationExists;
        } else {
            return ReturnCopyResult.Successful;
        }
    }

    protected List<CopyDTO> getCopiesByMediumID(IMediumDTO mediumDTO) {
        List<CopyDTO> list = new LinkedList<>();
        _mapDomainToDto.keySet().stream().
                filter(i -> i.getMediumId() == mediumDTO.getId() && i.getMediaType().equalsIgnoreCase(mediumDTO.getType())).
                forEach(i -> list.add(_mapDomainToDto.get(i)));
        return list;
    }
}
