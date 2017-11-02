package at.fhv.team05.ObjectInterfaces;

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
