package at.fhv.team05.Application;

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


    public ReservationController(Class<Reservation> reservationClass) {
        super(reservationClass);
    }

    public boolean checkAvailability(IMediumDTO mediumDTO) {
        List<CopyDTO> list = new LinkedList<>();
        _controllerFacade.getCopiesByMedium(mediumDTO).stream().filter(item -> item.getRental() == null).forEach(list::add);
        return list.isEmpty();
    }

    public void reserveMedium(CopyDTO copyDTO, CustomerDTO customerDTO) {
        Reservation reservedObject = new Reservation();

        reservedObject.setMediumId(copyDTO.getId());
        reservedObject.setCustomer(_controllerFacade.getDomainCustomer(customerDTO));
        reservedObject.setMediaType(copyDTO.getClass().toString());
        reservedObject.setReservationDate(new Date(Calendar.getInstance().getTimeInMillis()));

        _repository.save(reservedObject);
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
