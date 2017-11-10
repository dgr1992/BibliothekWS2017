package at.fhv.team05.Application;

import com.mchange.v2.collection.MapEntry;

import org.apache.logging.log4j.core.util.KeyValuePair;

import at.fhv.team05.domain.Reservation;
import at.fhv.team05.dtos.ReservationDTO;

import java.util.LinkedList;

public class ReservationController extends BaseController<Reservation, ReservationDTO> {
    private static ReservationController _instance;

    private ReservationController(Class<Reservation> reservationClass) {
        super(reservationClass);
    }

    public static ReservationController getInstance(){
        if(_instance == null){
            _instance = new ReservationController(Reservation.class);
        }
        return _instance;
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

    public boolean existsReservationForMedium(int mediumID, String mediumTyp){
        for (Reservation reservation : _mapDomainToDto.keySet()) {
            if(reservation.getMediumId() == mediumID && reservation.getMediaType().contentEquals(mediumTyp)){
                return true;
            }
        }
        return false;
    }

    @Override
    protected ReservationDTO createDTO(Reservation object) {
        return null;
    }

    @Override
    protected boolean compareInput(Reservation object, ReservationDTO reservationDTO) {
        return false;
    }
}
