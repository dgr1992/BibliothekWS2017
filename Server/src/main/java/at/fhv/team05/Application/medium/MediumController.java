package at.fhv.team05.Application.medium;

import at.fhv.team05.Application.BaseController;
import at.fhv.team05.domain.IDomainObject;
import at.fhv.team05.domain.Reservation;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public abstract class MediumController<Medium extends IDomainObject, MediumDTO extends IMediumDTO> extends BaseController<Medium, MediumDTO> {
    private Repository<Reservation> _repositoryReservation;


    public MediumController(Class<Medium> medium) {
        super(medium);
        _repositoryReservation = RepositoryFactory.getRepositoryInstance(Reservation.class);
    }

    //TODO evtl in eigenen controller?
    public void reserveMedium(List<MediumDTO> mediumDTOList, CustomerDTO customerDTO) {
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

    }
}