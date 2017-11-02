package at.fhv.team05.ObjectInterfaces;

import at.fhv.team05.dtos.IMediumDTO;

import java.util.Date;

public interface IDvd extends IMediumDTO {

    int getId();

    String getTitle();

    String getAsin();

    Date getReleaseDate();

    ICategory getCategory();

    String getPublisher();

    String getDirector();

}
