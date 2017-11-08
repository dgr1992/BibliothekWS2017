package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.ICategory;
import at.fhv.team05.ObjectInterfaces.IDvd;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DvdDTO implements IMediumDTO, Serializable {

    private int id;
    private String title;
    private String asin;
    private Date releaseDate;
    private CategoryDTO category;
    private String publisher;
    private String director;
    private Map<String, Object> attributeMap;


    public DvdDTO(IDvd dvd) {

        id = dvd.getId();
        title = dvd.getTitle();
        asin = dvd.getAsin();
        releaseDate = dvd.getReleaseDate();
        category = new CategoryDTO(dvd.getCategory());
        publisher = dvd.getPublisher();
        director = dvd.getDirector();

        attributeMap = new HashMap<>();
        attributeMap.put("id", id);
        attributeMap.put("title", title);
        attributeMap.put("articleId", asin);
        attributeMap.put("releaseDate", releaseDate);
        attributeMap.put("category", category);
        attributeMap.put("publisher", publisher);
        attributeMap.put("prod", director);



    }

    public DvdDTO(String title, String director, String asin) {
        this.title = title;
        this.director = director;
        this.asin = asin;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    public String getAsin() {
        return asin;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public ICategory getCategory() {
        return null;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDirector() {
        return director;
    }

}
