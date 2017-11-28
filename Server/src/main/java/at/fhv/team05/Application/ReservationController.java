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

    /**
     * Get all copies that are available and not in status "rented", "present" or "reserved"
     * @param mediumDTO
     * @return
     */
    public boolean checkAvailability(IMediumDTO mediumDTO) {
        List<CopyDTO> list = new LinkedList<>();
        //Only add the copies that have status "available" --> available when the rental object is null
        _controllerFacade.getCopiesByMedium(mediumDTO).getListDTO().stream().filter(item -> item.getRental() == null && item.getCopyStatus().compareTo("present") != 0 && item.getCopyStatus().compareTo("reserved") != 0).forEach(list::add);
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

    public ResultListDTO<ReservationDTO> getReservationsByIdAndMediumType(int id, String type) {
        List<ReservationDTO> reservations = new LinkedList<>();
        _mapDomainToDto.keySet().stream().
                filter(i -> i.getMediumId() == id && i.getMediaType().equalsIgnoreCase(type)).
                forEach(i -> reservations.add(_mapDomainToDto.get(i)));
        return new ResultListDTO<>(reservations, null);
    }

    public void remove(Reservation reservation){
        ReservationDTO reservationDTO = _mapDomainToDto.remove(reservation);
        _mapDtoToDomain.remove(reservationDTO);
        _repository.deleteById(reservation.getId());
    }

    public void remove(ReservationDTO reservationDTO){
        Reservation reservation = _mapDtoToDomain.remove(reservationDTO);
        _mapDomainToDto.remove(reservation);
        _repository.deleteById(reservation.getId());
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
