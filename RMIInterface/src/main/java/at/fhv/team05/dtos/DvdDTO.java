package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IDvd;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(value = {"attributeMap"})
public class DvdDTO implements IMediumDTO, Serializable {

    private int id;
    private String title;
    private String type;
    private String asin;
    private Date releaseDate;
    private String publisher;
    private String director;
    private Map<String, Object> attributeMap;

    /**
     * Default constructor for converting JSON into an Object
     */
    public DvdDTO() {
    }

    public DvdDTO(IDvd dvd) {

        id = dvd.getId();
        title = dvd.getTitle();
        type = dvd.getType();
        asin = dvd.getAsin();
        releaseDate = dvd.getReleaseDate();
        publisher = dvd.getPublisher();
        director = dvd.getDirector();

        attributeMap = new HashMap<>();
        attributeMap.put("id", id);
        attributeMap.put("title", title);
        attributeMap.put("articleId", asin);
        attributeMap.put("releaseDate", releaseDate);
        attributeMap.put("publisher", publisher);
        attributeMap.put("prod", director);


    }

    public DvdDTO(String title, String director, String asin, String type) {
        this.title = title;
        this.director = director;
        this.asin = asin;
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

    public String getAsin() {
        return asin;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDirector() {
        return director;
    }

}
