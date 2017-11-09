package at.fhv.team05.Application;

import at.fhv.team05.domain.Copy;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.IMediumDTO;

import java.util.LinkedList;
import java.util.List;

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

    public CopyDTO searchCopyByCopyNumber(int copyNumber) {

        for (Copy copy : _mapDomainToDto.keySet()) {
            if (copy.getCopyNumber() == copyNumber) {
                return _mapDomainToDto.get(copy);
            }
        }
        return null;
    }

    protected List<CopyDTO> getCopiesByMediumID(IMediumDTO mediumDTO) {
        List<CopyDTO> list = new LinkedList<>();
        _mapDomainToDto.keySet().stream().
                filter(i -> i.getId() == mediumDTO.getId() && i.getMediaType().equalsIgnoreCase(mediumDTO.getType())).
                forEach(i -> list.add(_mapDomainToDto.get(i)));
        return list;
    }
}
