package at.fhv.team05.Application;

import at.fhv.team05.domain.Reservation;
import at.fhv.team05.dtos.ReservationDTO;

import java.util.LinkedList;

public class ReservationController extends BaseController<Reservation, ReservationDTO> {


    public ReservationController(Class<Reservation> reservationClass) {
        super(reservationClass);
    }

    public boolean checkAvailability() {


        return true;
    }

    public ReservationDTO reserveMedium(LinkedList<ReservationDTO> mediumToReserve) {

        for (ReservationDTO reserve : mediumToReserve) {
            
        }


        return null;
    }

    /* public void reserveMedium(List<MediumDTO> mediumDTOList, CustomerDTO customerDTO) {
        LinkedList<MediumDTO> reserveList = new LinkedList<>();
        reserveList.addAll(mediumDTOList);

        for (MediumDTO medium : reserveList) {

            Reservation reservedObject = new Reservation();

            reservedObject.setMediumId(medium.getId());
            reservedObject.setCustomerId(customerDTO.getCustomerId());
            reservedObject.setMediaType(medium.getClass().toString());
            reservedObject.setReservationDate(new Date(Calendar.getInstance().getTimeInMillis()));

            _repositoryReservation.save(reservedObject);
        }

    }*/


    @Override
    protected ReservationDTO createDTO(Reservation object) {
        return null;
    }

    @Override
    protected boolean compareInput(Reservation object, ReservationDTO reservationDTO) {
        return false;
    }
}
