package at.fhv.team05.Application;

import at.fhv.team05.domain.IMedium;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.LinkedList;

public abstract class MediumController<Medium extends IMedium, MediumDTO extends IMediumDTO> {
    private Repository<Medium> _repository;
    private HashSet<Medium> _allMediums;
    private static Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    private static final Logger log = LogManager.getLogger(currentClass);


    public MediumController(Class<Medium> medium) {
        _repository = RepositoryFactory.getRepositoryInstance(medium);
        _allMediums = new HashSet<>();
        _allMediums.addAll(_repository.list());
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

    protected abstract boolean compareInput(Medium medium, MediumDTO mediumDTO);

    protected abstract MediumDTO createDTO(Medium medium);
}
