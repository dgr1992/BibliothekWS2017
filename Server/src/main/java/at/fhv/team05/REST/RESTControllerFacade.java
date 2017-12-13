package at.fhv.team05.REST;


import at.fhv.team05.Utility.JSONUtils;
import at.fhv.team05.library.ResultListDTO;
import at.fhv.team05.library.dtos.BookDTO;
import at.fhv.team05.library.dtos.BookDTO;
import at.fhv.team05.library.dtos.CopyDTO;
import at.fhv.team05.library.dtos.DvdDTO;
import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.server.Application.ControllerFacade;
import at.fhv.team05.server.domain.Copy;
import at.fhv.team05.server.domain.medium.Book;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

// The Java class will be hosted at the URI path "/helloworld"
@ApplicationPath("/")
@Path("/")
public class RESTControllerFacade extends Application {

    private final ControllerFacade _controllerFacade;

    public RESTControllerFacade() {
        _controllerFacade = ControllerFacade.getInstance();
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(RESTControllerFacade.class);
        return h;
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("/helloWorld")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }

    @POST
    @Produces("application/json")
    @Path("/searchBooks")
    public String searchForBook(String jsonBook) {
        BookDTO book = (BookDTO) JSONUtils.JSONToObject(jsonBook, BookDTO.class);
        ResultListDTO<BookDTO> resultBook = _controllerFacade.searchForBook(book);
        return JSONUtils.objectToJSON(resultBook.getListDTO());
    }

    @GET
    @Produces("application/json")
    @Path("/searchDvds")
    public String searchForDvd(String jsonDvd) {
        DvdDTO dvd = (DvdDTO) JSONUtils.JSONToObject(jsonDvd, DvdDTO.class);
        ResultListDTO<DvdDTO> resultDvd = _controllerFacade.searchForDvd(dvd);
        return JSONUtils.objectToJSON(resultDvd.getListDTO());
    }

    @GET
    @Produces("application/json")
    @Path("/getCopiesByMedium")
    public String getCopiesByMedium(String mediumDTO) {
        IMediumDTO medium = (IMediumDTO) JSONUtils.JSONToObject(mediumDTO, IMediumDTO.class);
        ResultListDTO<CopyDTO> resultCopies = _controllerFacade.getCopiesByMedium(medium);
        return JSONUtils.objectToJSON(resultCopies.getListDTO());
    }

    @GET
    @Produces("application/json")
    @Path("/getAllBooks")
    public String getAllBooks() {
        return _controllerFacade.getAllBooksJSON();
    }
}