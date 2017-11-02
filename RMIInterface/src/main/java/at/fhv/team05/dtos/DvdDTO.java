package at.fhv.team05.dtos;

import at.fhv.team05.dtos.ICategory;
import at.fhv.team05.dtos.IDvd;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class DvdDTO implements Serializable{

    private int id;
    private String title;
    private String asin;
    private Date releaseDate;
    private ICategory categoryId;
    private String publisher;
    private String director;


    public DvdDTO(IDvd dvd) {

        id = dvd.getId();
        title = dvd.getTitle();
        asin = dvd.getAsin();
        releaseDate = dvd.getReleaseDate();
        categoryId = dvd.getCategory();
        publisher = dvd.getPublisher();
        director = dvd.getDirector();

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
