package at.fhv.team05.REST;


import at.fhv.team05.Application.ControllerFacade;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.Utility.JSONUtils;
import at.fhv.team05.dtos.DvdDTO;

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
public class RESTControllerFacade extends Application implements IRESTControllerFacade {

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

    @GET
    @Produces("application/json")
    @Path("/searchBooks")
    @Override
    public String searchForBook(String jsonBook) {
        JsonReader reader = Json.createReader(new StringReader(jsonBook));
        JsonObject book = reader.readObject();
        reader.close();


        return "searchBook";
    }

    @GET
    @Produces("application/json")
    @Path("/searchDvds")
    @Override
    public String searchForDvd(String jsonDvd) {
        DvdDTO dvd = (DvdDTO) JSONUtils.JSONToObject(jsonDvd, DvdDTO.class);
        ResultListDTO<DvdDTO> resultDvd = _controllerFacade.searchForDvd(dvd);
        return JSONUtils.objectToJSON(resultDvd);
    }

    @GET
    @Produces("application/json")
    @Path("/getCopiesByMedium")
    @Override
    public String getCopiesByMedium(String mediumDTO) {
        return null;
    }
}