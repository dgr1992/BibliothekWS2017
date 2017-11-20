package at.fhv.team05.Application;

import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.domain.Reservation;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.dtos.ReservationDTO;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ReservationController extends BaseController<Reservation, ReservationDTO> {
    private static ReservationController _instance;

    private ReservationController(Class<Reservation> reservationClass) {
        super(reservationClass);
    }

    public static ReservationController getInstance() {
        if (_instance == null) {
            _instance = new ReservationController(Reservation.class);
        }
        return _instance;
    }

    public boolean checkAvailability(IMediumDTO mediumDTO) {
        List<CopyDTO> list = new LinkedList<>();
        _controllerFacade.getCopiesByMedium(mediumDTO).getListDTO().stream().filter(item -> item.getRental() == null).forEach(list::add);
        return !list.isEmpty();
    }

    public ResultDTO<Boolean> reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) {
        Reservation reservedObject = new Reservation();

        reservedObject.setMediumId(mediumDTO.getId());
        reservedObject.setCustomer(_controllerFacade.getDomainCustomer(customerDTO));
        reservedObject.setMediaType(mediumDTO.getType());
        reservedObject.setReservationDate(new Date(Calendar.getInstance().getTimeInMillis()));
        try {
            save(reservedObject);
            return new ResultDTO<>(true, null);
        } catch (Exception e) {
            return new ResultDTO<>(false, null);
        }
    }

    public boolean existsReservationForMedium(int mediumID, String mediumTyp) {
        for (Reservation reservation : _mapDomainToDto.keySet()) {
            if (reservation.getMediumId() == mediumID && reservation.getMediaType().contentEquals(mediumTyp)) {
                return true;
            }
        }
        return false;
    }

    public ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium) {
        List<ReservationDTO> reservations = new LinkedList<>();
        _mapDomainToDto.keySet().stream().
                filter(i -> i.getMediumId() == medium.getId() && i.getMediaType().equalsIgnoreCase(medium.getType())).
                forEach(i -> reservations.add(_mapDomainToDto.get(i)));
        return new ResultListDTO<>(reservations, null);
    }

    @Override
    protected ReservationDTO createDTO(Reservation object) {
        return new ReservationDTO(object);
    }

    @Override
    protected boolean compareInput(Reservation object, ReservationDTO reservationDTO) {
        return false;
    }
}
