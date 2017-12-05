package at.fhv.team05.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONUtils {

    /**
     * Converts an Object into a Json string
     * @param obj the Object that needs to be converted
     * @return JSON String
     */
    public static String objectToJSON(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts a JSON String into an Object.
     * The resulting object needs a default constructor
     * @param json The JSON-String that should be converted into an Object.
     * @param clazz The object-class
     * @return The converted object
     */
    public static Object JSONToObject(String json, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = null;
        try {
            obj = mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
