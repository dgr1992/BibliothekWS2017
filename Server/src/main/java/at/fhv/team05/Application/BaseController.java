package at.fhv.team05.Application;

import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
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

    public ResultDTO<DomainDTO> searchById(int id) {
        for (DomainObject obj : _mapDomainToDto.keySet()) {
            if (obj.getId() == id) {
                return new ResultDTO<>(_mapDomainToDto.get(obj), null);
            }
        }
        return null;
    }

    public ResultListDTO<DomainDTO> searchFor(DomainDTO dto) {
        ResultListDTO<DomainDTO> list = new ResultListDTO<>();
        LinkedList<DomainDTO> dtos = new LinkedList<>();
        for (DomainObject obj : _mapDomainToDto.keySet()) {
            if (compareInput(obj, dto)) {
                dtos.add(_mapDomainToDto.get(obj));
            }
        }
        list.setListDTO(dtos);
        list.setException(null);
        return list;
    }

    public DomainDTO getDTO(DomainObject obj) {
        return _mapDomainToDto.get(obj);
    }

    public DomainObject getDomain(DomainDTO dto) {
        return _mapDtoToDomain.get(dto);
    }

    public void save(DomainObject obj) {
        _repository.save(obj);
        fillMap();
    }

//    public void delete(DomainObject obj) {
//        _repository.delete(obj);
//        fillMap();
//    }

    protected abstract DomainDTO createDTO(DomainObject object);

    protected abstract boolean compareInput(DomainObject object, DomainDTO dto);
}
