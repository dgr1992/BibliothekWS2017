package at.fhv.team05.REST;


import at.fhv.team05.Utility.JSONUtils;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.ResultListDTO;
import at.fhv.team05.library.dtos.*;
import at.fhv.team05.server.Application.ControllerFacade;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.io.StringReader;
import java.sql.Date;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("REST")
@Path("/")
public class RESTControllerFacade extends Application {

    private ControllerFacade _controllerFacade;

    public RESTControllerFacade() {

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
    public String searchForBook(String jsonBook) {
        init(); //Init all needed controllers and database

        BookDTO book = (BookDTO) JSONUtils.JSONToObject(jsonBook, BookDTO.class);
        ResultListDTO<BookDTO> resultBook = _controllerFacade.searchForBook(book);
        String json = JSONUtils.objectToJSON(resultBook.getListDTO());

        close(); //Close everything opened in init
        return json;
    }

    private boolean checkPermission(HttpServletRequest headers) {
        //Get the authorization header entry
        Enumeration<String> authHeaders = headers.getHeaders(HttpHeaders.AUTHORIZATION);

        if (authHeaders.hasMoreElements()) {
            //Get the value of the authorization header
            String auth = authHeaders.nextElement();

            //Split the string into the needed parts
            String[] authSplitted = auth.split(" ");
            String authType = authSplitted[0]; // eg. hmac
            String userAndHash = authSplitted[1]; //Contains user:user:password
            String user = userAndHash.split(":")[0];//user
            String hashedUserAndPassword = userAndHash.split(":")[1];//user:password

            //TODO secret in db? speichern
            String secret = "testKey";

            //Check for user remotelibrary with password remote1234
            if (user.equals("remotelibrary")) {
                //String serverDigest = HmacUtils.hmacSha1Hex(secret.getBytes(), sb.toString().getBytes());
                String toHash = "remotelibrary:remote1234";

                String serverDigest = generateHashed(secret, toHash);
                if (serverDigest == null) {
                    return false;
                }
                return serverDigest.equals(hashedUserAndPassword);
            }
            return false;
        } else {
            return false;
        }
    }

    private String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(String.format("%02x", bytes[i]));
        }
        return sb.toString();
    }

    private String generateHashed(String key, String digest) {
        Mac localMac;
        try {
            localMac = Mac.getInstance("HmacSHA1");
            localMac.init(new SecretKeySpec(key.getBytes("UTF-8"), localMac.getAlgorithm()));
            byte[] result = localMac.doFinal(digest.getBytes("UTF-8"));
            String hexString = toHexString(result);
            return hexString;
        } catch (Exception ex) {
            return null;
        }
    }

    @POST
    @Produces("application/json")
    @Path("/searchDvds")
    public String searchForDvd(String jsonDvd) {
        init(); //Init all needed controllers and database

        DvdDTO dvd = (DvdDTO) JSONUtils.JSONToObject(jsonDvd, DvdDTO.class);
        ResultListDTO<DvdDTO> resultDvd = _controllerFacade.searchForDvd(dvd);

        String json = JSONUtils.objectToJSON(resultDvd.getListDTO());

        close(); //Close everything opened in init
        return json;
    }

    @POST
    @Produces("application/json")
    @Path("/getCopiesByMedium")
    public String getCopiesByMedium(String jsonMedium) {
        init(); //Init all needed controllers and database

        JsonReader reader = Json.createReader(new StringReader(jsonMedium));
        JsonObject medium = reader.readObject();
        Class clazz = (medium.getString("type").equalsIgnoreCase("book") ? BookDTO.class : DvdDTO.class);
        IMediumDTO iMedium = (IMediumDTO) JSONUtils.JSONToObject(jsonMedium, clazz);
        ResultListDTO<CopyDTO> resultCopies = _controllerFacade.getCopiesByMedium(iMedium);

        String json = JSONUtils.objectToJSON(resultCopies.getListDTO());

        close(); //Close everything opened in init
        return json;
    }

    @GET
    @Produces("application/json")
    @Path("/getAllBooks")
    public String getAllBooks() {
        init(); //Init all needed controllers and database

        String json = _controllerFacade.getAllBooksJSON();

        close(); //Close everything opened in init
        return json;
    }


    @POST
    @Produces("application/json")
    @Path("/rentMedium")
    public String rentMedium(@Context HttpServletRequest request, String jsonObject) {
        init(); //Init all needed controllers and database

        if (!checkPermission(request)) {
            return "Permission denied!";
        }

        //Read the json string
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

        String json = JSONUtils.objectToJSON(resultMSG);

        close(); //Close everything opened in init
        return json;
    }

    @GET
    @Produces("application/json")
    @Path("/authenticateUser")
    public String authenticateUser(@Context HttpServletRequest request) {
        init(); //Init all needed controllers and database
        if (checkPermission(request)) {
            return "true";
        }
        close(); //Close everything opened in init
        return "false";
    }

    private void init() {
//        DatabaseConnection.init();
        _controllerFacade = ControllerFacade.getInstance();
    }

    private void close() {
//        try {
//            DatabaseConnection.close();
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }

    }
}