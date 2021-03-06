package at.fhv.team05.server.Application.medium;

import at.fhv.team05.server.Utility.StringUtilities;
import at.fhv.team05.server.domain.medium.Dvd;
import at.fhv.team05.library.dtos.DvdDTO;

public class DvdController extends MediumController<Dvd, DvdDTO> {


    private static DvdController mInstance;

    private DvdController(Class<Dvd> medium) {
        super(medium);
    }

    public static DvdController getInstance() {
        if (mInstance == null) {
            mInstance = new DvdController(Dvd.class);
        }
        return mInstance;
    }

    @Override
    protected boolean compareInput(Dvd dvd, DvdDTO dvdDTO) {
        return StringUtilities.containsIgnoreCase(dvd.getTitle(), dvdDTO.getTitle())
                && StringUtilities.containsIgnoreCase(dvd.getDirector(), dvdDTO.getDirector())
                && StringUtilities.containsIgnoreCase(dvd.getAsin(), dvdDTO.getAsin());
    }

    @Override
    protected DvdDTO createDTO(Dvd dvd) {
        return new DvdDTO(dvd);
    }
}
