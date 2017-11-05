package at.fhv.team05.Application;

import java.util.HashSet;
import java.util.LinkedList;

import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Daniel on 05.11.2017.
 */
public class CopyController {
    private Repository<Copy> _repositoryCopy;
    private HashSet<Copy> _allCopies;
    private static Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    private static final Logger log = LogManager.getLogger(currentClass);

    private static CopyController _theInstance;

    private CopyController() {
        _repositoryCopy = RepositoryFactory.getRepositoryInstance(Copy.class);

        _allCopies = new HashSet<>();
        _allCopies.addAll(_repositoryCopy.list());
    }

    public static CopyController getInstance(){
        if(_theInstance == null){
            _theInstance = new CopyController();
        }
        return _theInstance;
    }

    public LinkedList<CopyDTO> searchForCopy(CopyDTO copyDTO) {
        LinkedList<CopyDTO> result = new LinkedList<>();
        for (Copy copy : _allCopies) {
            if (compareInput(copy, copyDTO)) {
                result.add(createDTO(copy));
            }
        }
        return result;
    }

    /**
     * Compares the copies by their copy number
     * @param copy
     * @param copyDTO
     * @return returns true if the numbers match
     */
    private boolean compareInput(Copy copy, CopyDTO copyDTO){
        return copy.getCopyNumber() == copyDTO.getCopyNumber();
    }

    private CopyDTO createDTO(Copy copy){
        return new CopyDTO(copy);
    }
}
