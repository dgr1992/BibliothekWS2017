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
    /**
     * Controller facade has all available methods and forwards them to the corresponding controller.
     */
    protected static ControllerFacade _controllerFacade = ControllerFacade.getInstance();
    /**
     * The repository for the corresponding domain-object.
     */
    protected Repository<DomainObject> _repository;
    /**
     * HashMap which maps a domain-object to the equivalent dto.
     */
    protected HashMap<DomainObject, DomainDTO> _mapDomainToDto;
    /**
     * HashMap which maps a dto to the equivalent domain-object.
     */
    protected HashMap<DomainDTO, DomainObject> _mapDtoToDomain;

    /**
     * CurrentClass is the Class-object from the enclosingClass.
     */
    protected static Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    protected static final Logger _log = LogManager.getLogger(currentClass);

    public BaseController(Class<DomainObject> domainObjectClass) {
        //create the corresponding repository for the domain-object.
        _repository = RepositoryFactory.getRepositoryInstance(domainObjectClass);
        _mapDomainToDto = new HashMap<>();
        _mapDtoToDomain = new HashMap<>();
        fillMap();
    }

    /**
     * This method fills _mapDomainToDto and _mapDtoToDomain maps with objects from the repository.
     */
    public void fillMap() {
        _mapDomainToDto = new HashMap<>();
        _mapDtoToDomain = new HashMap<>();
        for (DomainObject obj : _repository.list()) {
            DomainDTO dto = createDTO(obj);
            _mapDomainToDto.put(obj, dto);
            _mapDtoToDomain.put(dto, obj);
        }
    }

    /**
     * @param id from the object
     * @return ResultDTO with the dto-object
     */
    public ResultDTO<DomainDTO> searchById(int id) {
        for (DomainObject obj : _mapDomainToDto.keySet()) {
            if (obj.getId() == id) {
                return new ResultDTO<>(_mapDomainToDto.get(obj), null);
            }
        }
        return null;
    }

    /**
     * This method can be used to get a list of Dtos which return true from the compareInput(..) method.
     * For example to get all books which contain a specific string in the title.
     *
     * @param dto of the domain-object.
     * @return a list with results
     */
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

    /**
     * @param obj domain-object
     * @return corresponding dto
     */
    public DomainDTO getDTO(DomainObject obj) {
        return _mapDomainToDto.get(obj);
    }

    /**
     * @param dto dto-object
     * @return corresponding domain-object
     */
    public DomainObject getDomain(DomainDTO dto) {
        return _mapDtoToDomain.get(dto);
    }

    /**
     * saves the domain-object to the repository and refills the hashMaps.
     *
     * @param obj
     */
    public void save(DomainObject obj) {
        _repository.save(obj);
        fillMap();
    }

//    public void delete(DomainObject obj) {
//        _repository.delete(obj);
//        fillMap();
//    }

    /**
     * @param object domain-object.
     * @return a new DTO object with the values from the domain-object.
     */
    protected abstract DomainDTO createDTO(DomainObject object);

    /**
     * Booleanoperation which is used in the searchFor(..) method.
     *
     * @param object domain-object
     * @param dto    dto-object
     * @return boolean
     */
    protected abstract boolean compareInput(DomainObject object, DomainDTO dto);
}
