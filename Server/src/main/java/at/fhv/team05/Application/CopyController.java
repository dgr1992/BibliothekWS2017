package at.fhv.team05.Application;

import at.fhv.team05.domain.Copy;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.persistence.RepositoryFactory;

public class CopyController extends BaseController<Copy, CopyDTO> {
    private static CopyController _theInstance;

    private CopyController() {
        super(Copy.class);
    }

    public static CopyController getInstance() {
        if (_theInstance == null) {
            _theInstance = new CopyController();
        }
        return _theInstance;
    }

    /**
     * Gets the domain object that the CopyDTO represents
     * @param copyDTO
     * @return
     */
    public Copy getCopyFor(CopyDTO copyDTO){
        for (Copy copy : RepositoryFactory.getRepositoryInstance(Copy.class).list()) {
            if (copy.getId() == copyDTO.getId()) {
                return copy;
            }
        }
        return null;
    }

    /**
     * Compares the copies by their copy number
     *
     * @param copy
     * @param copyDTO
     * @return returns true if the numbers match
     */
    @Override
    protected boolean compareInput(Copy copy, CopyDTO copyDTO) {
        return copy.getCopyNumber() == copyDTO.getCopyNumber();
    }

    @Override
    protected CopyDTO createDTO(Copy copy) {
        return new CopyDTO(copy);
    }
}
