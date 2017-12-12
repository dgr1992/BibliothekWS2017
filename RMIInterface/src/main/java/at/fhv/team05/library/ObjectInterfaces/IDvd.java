package at.fhv.team05.library.ObjectInterfaces;

import at.fhv.team05.library.dtos.IMediumDTO;

import java.util.Date;

public interface IDvd extends IMediumDTO {

    @Override
    int getId();

    @Override
    String getTitle();

    String getAsin();

    Date getReleaseDate();

    String getPublisher();

    String getDirector();

}
