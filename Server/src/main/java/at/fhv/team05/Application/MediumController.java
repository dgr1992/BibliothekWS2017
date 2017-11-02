package at.fhv.team05.Application;

import at.fhv.team05.domain.IMedium;
import at.fhv.team05.domain.Reservation;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class MediumController<Medium extends IMedium, MediumDTO extends IMediumDTO> {
    private Repository<Medium> _repositoryMedium;
    private Repository<Reservation> _repositoryReservation;
    private HashSet<Medium> _allMediums;
    private static Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    private static final Logger log = LogManager.getLogger(currentClass);


    public MediumController(Class<Medium> medium) {
        _repositoryMedium = RepositoryFactory.getRepositoryInstance(medium);
        _repositoryReservation = RepositoryFactory.getRepositoryInstance(Reservation.class);

        _allMediums = new HashSet<>();
        _allMediums.addAll(_repositoryMedium.list());
    }

    public LinkedList<MediumDTO> searchForMedium(MediumDTO mediumDTO) {
        LinkedList<MediumDTO> result = new LinkedList<>();
        for (Medium medium : _allMediums) {
            if (compareInput(medium, mediumDTO)) {
                result.add(createDTO(medium));
            }
        }
        return result;
    }

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


    protected abstract boolean compareInput(Medium medium, MediumDTO mediumDTO);

    protected abstract MediumDTO createDTO(Medium medium);
}
