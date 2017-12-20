package at.fhv.team05.server.Application.medium;

import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.server.Application.BaseController;
import at.fhv.team05.server.domain.IDomainObject;

public abstract class MediumController<Medium extends IDomainObject, MediumDTO extends IMediumDTO> extends BaseController<Medium, MediumDTO> {
    public MediumController(Class<Medium> medium) {
        super(medium);
    }


}


