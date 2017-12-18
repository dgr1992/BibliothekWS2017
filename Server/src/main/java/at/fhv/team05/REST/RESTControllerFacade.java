package at.fhv.team05.REST;


import at.fhv.team05.Utility.JSONUtils;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.ResultListDTO;
import at.fhv.team05.library.dtos.*;
import at.fhv.team05.server.Application.ControllerFacade;
import at.fhv.team05.server.Application.LdapController;
import at.fhv.team05.server.domain.UserAccount;
import org.apache.commons.codec.digest.HmacUtils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.io.StringReader;
import java.sql.Date;
import java.util.Calendar;
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

    //in our case we don't necessarily need this method because we only have one REST-class
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(RESTControllerFacade.class);
        return h;
    }

    @POST
    @Produces("application/json")
    @Path("/searchBooks")
    public String searchForBook(@Context HttpServletRequest request, String jsonBook) {

        if (checkPermission(request)) {
            return "Permission denied!";
        }

        BookDTO book = (BookDTO) JSONUtils.JSONToObject(jsonBook, BookDTO.class);
        ResultListDTO<BookDTO> resultBook = _controllerFacade.searchForBook(book);
        return JSONUtils.objectToJSON(resultBook.getListDTO());
    }

    private boolean checkPermission(HttpServletRequest headers) {
        // Header must contain these fields
        // Authentication: username:[digest]
        // Date: date
        //digest = httpMethod+requestUri
        StringBuilder sb = new StringBuilder();
        sb.append(headers.getMethod()).append("+").append(headers.getRequestURI());

        String[] authSplitted = headers.getHeader("Authentication").split(":");
        String username = authSplitted[0];
        String clientDigest = authSplitted[1];

        //TODO secret in db? speichern
        String secret = "testKey";

        String serverDigest = HmacUtils.hmacSha1Hex(secret.getBytes(), sb.toString().getBytes());

        return serverDigest.equals(clientDigest);
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
        Class clazz = (medium.getString("type").equalsIgnoreCase("book") ? BookDTO.class : DvdDTO.class);
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
    public String rentMedium(String jsonObject) {
        /*
        JsonReader reader = Json.createReader(new StringReader(jsonObject));
        JsonObject object = reader.readObject();

        int customerNumber = Integer.getInteger(object.getString("customerNumber").replace('"',' ').trim());
        int copyNumber = Integer.getInteger(object.getString("copyNumber").replace('"',' ').trim());
        */

        //#RealDirtyFix
        String cleanedData = jsonObject.replace('{', ' ').replace('}', ' ').replace('"', ' ').trim();
        String[] data1 = cleanedData.split(",")[0].split(":");
        String[] data2 = cleanedData.split(",")[1].split(":");

        int customerNumber = 0;
        int copyNumber = 0;
        if (data1[0].contains("customerNumber")) {
            customerNumber = Integer.parseInt(data1[1].trim());
        }
        if (data1[0].contains("copyNumber")) {
            copyNumber = Integer.parseInt(data1[1].trim());
        }
        if (data2[0].contains("customerNumber")) {
            customerNumber = Integer.parseInt(data2[1].trim());
        }
        if (data2[0].contains("copyNumber")) {
            copyNumber = Integer.parseInt(data2[1].trim());
        }


        CustomerDTO customerDTO = _controllerFacade.searchForCustomer(new CustomerDTO(customerNumber, "", "")).getListDTO().get(0);
        CopyDTO copyDTO = _controllerFacade.searchCopyByCopyNumber(copyNumber).getDto();

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setCopy(copyDTO);
        rentalDTO.setCustomer(customerDTO);
        rentalDTO.setPickupDate(new Date(System.currentTimeMillis()));
        rentalDTO.setReturnDate(null);
        String loanPeriodName;
        if (copyDTO.getMediaType().equalsIgnoreCase("book")) {
            loanPeriodName = "BookLoanPeriod";
        } else {
            loanPeriodName = "DVDLoanPeriod";
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, Integer.parseInt(_controllerFacade.getConfigDTOFor(loanPeriodName).getDto().getValue()));

        rentalDTO.setDeadline(new Date(cal.getTimeInMillis()));
        rentalDTO.setExtendCounter(0);

        ResultDTO<Boolean> result = _controllerFacade.rentMedium(rentalDTO);
        String resultMSG = (result.getException() == null ? "SUCCESS" : result.getException().getMessage());

        return JSONUtils.objectToJSON(resultMSG);
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
        return JSONUtils.objectToJSON(tmpDTO);
    }
}