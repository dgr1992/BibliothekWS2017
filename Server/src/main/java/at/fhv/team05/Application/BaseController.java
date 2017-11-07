package at.fhv.team05.Application;

import at.fhv.team05.domain.IDomainObject;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class BaseController<DomainObject extends IDomainObject, DomainDTO> {
    protected Repository<DomainObject> _repository;
    protected HashMap<DomainObject, DomainDTO> _map;
    protected static Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    protected static final Logger log = LogManager.getLogger(currentClass);

    public BaseController(Class<DomainObject> domainObjectClass) {
        _repository = RepositoryFactory.getRepositoryInstance(domainObjectClass);

        _map = new HashMap<>();
        fillMap();
    }

    private void fillMap() {
        for (DomainObject obj : _repository.list()) {
            _map.put(obj, createDTO(obj));
        }
    }

    public DomainDTO searchById(int id) {
        for (DomainObject obj : _map.keySet()) {
            if (obj.getId() == id) {
                return _map.get(obj);
            }
        }
        return null;
    }

    public LinkedList<DomainDTO> searchFor(DomainDTO dto) {
        LinkedList<DomainDTO> result = new LinkedList<>();
        for (DomainObject obj : _map.keySet()) {
            if (compareInput(obj, dto)) {
                result.add(_map.get(obj));
            }
        }
        return result;
    }

    public DomainDTO getDTO(DomainObject obj) {
        return _map.get(obj);
    }

    protected abstract DomainDTO createDTO(DomainObject object);

    protected abstract boolean compareInput(DomainObject object, DomainDTO dto);
}
