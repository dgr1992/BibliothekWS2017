package at.fhv.team05.Application;

import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.Dvd;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.IDvd;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.LinkedList;

public class DvdController {
    private Repository<Dvd> _dvdRepository = null;
    private HashSet<Dvd> _dvdSet;
    private LinkedList<DvdDTO> _result;

    private static DvdController instance;
    private static final Logger log = LogManager.getLogger(DvdController.class);

    private DvdController() {
        _dvdRepository = RepositoryFactory.getRepositoryInstance(Dvd.class);
        _dvdSet = new HashSet<>();

        _dvdSet.addAll(_dvdRepository.list());
    }

    public static DvdController getInstance() {
        if (instance == null) {
            instance = new DvdController();
        }
        return instance;
    }

    public LinkedList<DvdDTO> searchForDvd(String title, String director, String asin) throws RemoteException {
        _result = new LinkedList<>();

        try {

            for (Dvd dvd : _dvdSet) {
                if (StringUtilities.containsIgnoreCase(dvd.getTitle(), title)
                        && StringUtilities.containsIgnoreCase(dvd.getDirector(), director)
                        && StringUtilities.containsIgnoreCase(dvd.getAsin(), asin)) {
                    _result.add(new DvdDTO(dvd));
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return _result;
    }
}
