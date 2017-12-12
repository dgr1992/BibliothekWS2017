package at.fhv.team05.server.Application.medium;

import at.fhv.team05.server.Application.BaseController;
import at.fhv.team05.server.domain.IDomainObject;
import at.fhv.team05.server.domain.Reservation;
import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.server.persistence.Repository;
import at.fhv.team05.server.persistence.RepositoryFactory;

public abstract class MediumController<Medium extends IDomainObject, MediumDTO extends IMediumDTO> extends BaseController<Medium, MediumDTO> {
    private Repository<Reservation> _repositoryReservation;


    public MediumController(Class<Medium> medium) {
        super(medium);
        _repositoryReservation = RepositoryFactory.getRepositoryInstance(Reservation.class);
    }


}


