package at.fhv.team05.server.Application;

import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.ResultListDTO;
import at.fhv.team05.server.domain.Copy;
import at.fhv.team05.library.dtos.CopyDTO;
import at.fhv.team05.library.dtos.IMediumDTO;

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

    /**
     * Searches for a copy by copy number
     * @param copyNumber
     * @return
     */
    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) {

        for (Copy copy : _mapDomainToDto.keySet()) {
            if (copy.getCopyNumber() == copyNumber) {
                return new ResultDTO<>(_mapDomainToDto.get(copy), null);
            }
        }
        return null;
    }

    /***
     * Resets a copy as available. Reverences to a rental will be removed.
     * @param copyDTO
     * @return
     */
    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) {
        Copy copy = _controllerFacade.getDomainCopy(copyDTO);
        if (copy.getRental() == null) {
            return new ResultDTO<>(false, new Exception("The given copy is not lent."));
        }

        //Set the return Date
        Calendar currenttime = Calendar.getInstance();
        Date currentDate = new Date((currenttime.getTime()).getTime());
        copy.getRental().setReturnDate(currentDate);

        //Remove the rental
        copy.setRental(null);

        //Check if a reservation exists
        boolean reservationExists = _controllerFacade.existsReservationForMedium(copy.getMediumId(), copy.getMediaType());
        if(reservationExists){
            copy.setCopyStatus("reserved");
        } else {
            copy.setCopyStatus("available");
        }

        save(copy);
        //Also force a update of the rental repository. Save of copy does not update the rentals
        RentalController.getInstance().fillMap();

        //Notify user if the medium has an open reservation
        if (reservationExists) {
            MessagingController messagingController = MessagingController.getInstance();
            messagingController.createReturnedCopyMessage(copyDTO);
            return new ResultDTO<>(true, new Exception("Return process successful. There is a reservation for this media."));
        } else {
            return new ResultDTO<>(true, new Exception("Return process successful."));
        }
    }

    /**
     * Searches for all copies for the given medium
     * @param mediumDTO
     * @return
     */
    protected ResultListDTO<CopyDTO> getCopiesByMediumID(IMediumDTO mediumDTO) {
        List<CopyDTO> list = new LinkedList<>();
        _mapDomainToDto.keySet().stream().
                filter(i -> i.getMediumId() == mediumDTO.getId() && i.getMediaType().equalsIgnoreCase(mediumDTO.getType())).
                forEach(i -> list.add(_mapDomainToDto.get(i)));
        return new ResultListDTO<>(list, null);
    }
}
