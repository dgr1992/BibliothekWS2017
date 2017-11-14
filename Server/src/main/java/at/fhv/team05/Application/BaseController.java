package at.fhv.team05.Application;

import at.fhv.team05.domain.IDomainObject;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class BaseController<DomainObject extends IDomainObject, DomainDTO> {
    protected static ControllerFacade _controllerFacade = ControllerFacade.getInstance();
    protected Repository<DomainObject> _repository;
    protected HashMap<DomainObject, DomainDTO> _mapDomainToDto;
    protected HashMap<DomainDTO, DomainObject> _mapDtoToDomain;
    protected static Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    protected static final Logger log = LogManager.getLogger(currentClass);

    public BaseController(Class<DomainObject> domainObjectClass) {
        _repository = RepositoryFactory.getRepositoryInstance(domainObjectClass);

        _mapDomainToDto = new HashMap<>();
        _mapDtoToDomain = new HashMap<>();
        fillMap();
    }

    public void fillMap() {
        _mapDomainToDto = new HashMap<>();
        _mapDtoToDomain = new HashMap<>();
        for (DomainObject obj : _repository.list()) {
            DomainDTO dto = createDTO(obj);
            _mapDomainToDto.put(obj, dto);
            _mapDtoToDomain.put(dto, obj);
        }
    }

    public DomainDTO searchById(int id) {
        for (DomainObject obj : _mapDomainToDto.keySet()) {
            if (obj.getId() == id) {
                return _mapDomainToDto.get(obj);
            }
        }
        return null;
    }

    public LinkedList<DomainDTO> searchFor(DomainDTO dto) {
        LinkedList<DomainDTO> result = new LinkedList<>();
        for (DomainObject obj : _mapDomainToDto.keySet()) {
            if (compareInput(obj, dto)) {
                result.add(_mapDomainToDto.get(obj));
            }
        }
        return result;
    }

    public DomainDTO getDTO(DomainObject obj) {
        return _mapDomainToDto.get(obj);
    }

    public DomainObject getDomain(DomainDTO dto) {
        return _mapDtoToDomain.get(dto);
    }

    protected abstract DomainDTO createDTO(DomainObject object);

    protected abstract boolean compareInput(DomainObject object, DomainDTO dto);
}
