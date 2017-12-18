package at.fhv.team05.REST;


import at.fhv.team05.Utility.JSONUtils;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.ResultListDTO;
import at.fhv.team05.library.dtos.*;
import at.fhv.team05.library.dtos.BookDTO;
import at.fhv.team05.library.dtos.CopyDTO;
import at.fhv.team05.library.dtos.DvdDTO;
import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.server.Application.ControllerFacade;
import at.fhv.team05.server.Application.LdapController;
import at.fhv.team05.server.domain.UserAccount;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
@Path("/")
public class RESTControllerFacade extends Application {

    private final ControllerFacade _controllerFacade;
    private final String _key;
    private UserAccount _account = null;
    private static final int KEY_LENGTH = 32;

    public RESTControllerFacade() {
        _controllerFacade = ControllerFacade.getInstance();
        _key = LdapController.getRandomHexString(KEY_LENGTH);
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(RESTControllerFacade.class);
        return h;
    }

    @GET
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

    @POST
    @Produces("application/json")
    @Path("/searchDvds")
    public String searchForDvd(String jsonDvd) {
        DvdDTO dvd = (DvdDTO) JSONUtils.JSONToObject(jsonDvd, DvdDTO.class);
        ResultListDTO<DvdDTO> resultDvd = _controllerFacade.searchForDvd(dvd);
        return JSONUtils.objectToJSON(resultDvd.getListDTO());
    }

    @POST
    @Produces("application/json")
    @Path("/getCopiesByMedium")
    public String getCopiesByMedium(String jsonMedium) {
        JsonReader reader = Json.createReader(new StringReader(jsonMedium));
        JsonObject medium = reader.readObject();
        Class clazz = (medium.getString("type").equalsIgnoreCase("book")? BookDTO.class:DvdDTO.class);
        IMediumDTO iMedium = (IMediumDTO) JSONUtils.JSONToObject(jsonMedium, clazz);
        ResultListDTO<CopyDTO> resultCopies = _controllerFacade.getCopiesByMedium(iMedium);
        return JSONUtils.objectToJSON(resultCopies.getListDTO());
    }

    @GET
    @Produces("application/json")
    @Path("/getAllBooks")
    public String getAllBooks() {
        return _controllerFacade.getAllBooksJSON();
    }


    @POST
    @Produces("application/json")
    @Path("/rentMedium")
    public String rentMedium(String jsonRental) {
        RentalDTO rentalDTO = (RentalDTO) JSONUtils.JSONToObject(jsonRental, RentalDTO.class);
        ResultDTO<Boolean> result = _controllerFacade.rentMedium(rentalDTO);
        return JSONUtils.objectToJSON(result.getDto());

    }

    @POST
    @Produces("application/json")
    @Path("/searchCopyByCopyNumber")
    public String searchCopyByCopyNumber(int copyNumber) {
        ResultDTO<CopyDTO> resultDTO = _controllerFacade.searchCopyByCopyNumber(copyNumber);
        return JSONUtils.objectToJSON(resultDTO.getDto());
    }

    @POST
    @Produces("application/json")
    @Path("/authenticateUser")
    public String authenticateUser(String jsonAccount) {
        UserAccountDTO accountDTO = (UserAccountDTO) JSONUtils.JSONToObject(jsonAccount, UserAccountDTO.class);
        ResultDTO<Boolean> tmpDTO = _controllerFacade.authenticateUser(accountDTO, _key);
        //getDTO gibt true, falls login erfolgreich war, ansonsten false
        if (tmpDTO.getDto()) {
            _account = _controllerFacade.getDomainAccount(accountDTO);
        }
        return JSONUtils.objectToJSON(tmpDTO.getDto());
    }

    @POST
    @Produces("application/json")
    @Path("/getLoanPeriod")
    public String getLoanPeriodFor(String mediaType)  {
        ResultDTO<ConfigurationDataDTO> result;
        if (mediaType.equalsIgnoreCase("book")) {
            result = _controllerFacade.getConfigDTOFor("BookLoanPeriod");
        } else if ("dvd".equalsIgnoreCase(mediaType)){
            result = _controllerFacade.getConfigDTOFor("DVDLoanPeriod");
        } else {
            result = new ResultDTO<>(null, new Exception("No loan period for " + mediaType));
        }
        return JSONUtils.objectToJSON(result.getDto());
    }
}