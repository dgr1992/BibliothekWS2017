package at.fhv.team05.Utility;

import at.fhv.team05.library.dtos.BookDTO;
import at.fhv.team05.server.domain.medium.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Date;

public class JSONUtils {

    /**
     * Converts an Object into a Json string
     *
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
     *
     * @param json  The JSON-String that should be converted into an Object.
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

    public static void main(String[] args) {
        Book book = new Book();
        book.setID(1);
        book.setAuthor("danül");
        book.setIsbn("isbnblabla");
        book.setReleaseDate(new Date(System.currentTimeMillis()));
        book.setPublisher("Publisher");
        book.setTitle("Titään");

        BookDTO dto = new BookDTO(book);

        String str = objectToJSON(dto);

        System.out.println(str);
    }
}
