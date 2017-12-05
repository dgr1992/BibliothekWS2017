package at.fhv.team05.REST;

import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.IMediumDTO;

public interface IRESTControllerFacade {
    public String searchForBook(String book);

    public String searchForDvd(String dvd);

    public String getCopiesByMedium(String mediumDTO);
}
