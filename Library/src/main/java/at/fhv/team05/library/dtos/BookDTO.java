package at.fhv.team05.library.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import at.fhv.team05.library.ObjectInterfaces.IBook;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(value = { "attributeMap" })
public class BookDTO implements IMediumDTO, Serializable {

    private int id;
    private String title;
    private String type;
    private String isbn;
    private Date releaseDate;
    private String publisher;
    private String author;
    private Map<String, Object> attributeMap;

    /**
     * Default constructor for converting JSON into an Object
     */
    public BookDTO(){ }

    public BookDTO(IBook book) {

        id = book.getId();
        title = book.getTitle();
        type = book.getType();
        isbn = book.getIsbn();
        releaseDate = book.getReleaseDate();
        publisher = book.getPublisher();
        author = book.getAuthor();


        attributeMap = new HashMap<>();
        attributeMap.put("id", id);
        attributeMap.put("title", title);
        attributeMap.put("articleId", isbn);
        attributeMap.put("releaseDate", releaseDate);
        attributeMap.put("publisher", publisher);
        attributeMap.put("prod", author);

    }

    public BookDTO(String title, String author, String isbn, String type) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    public String getIsbn() {
        return isbn;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

}
