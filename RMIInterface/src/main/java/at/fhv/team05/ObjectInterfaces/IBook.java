package at.fhv.team05.ObjectInterfaces;

import at.fhv.team05.dtos.IMediumDTO;

import java.util.Date;

public interface IBook extends IMediumDTO {

    @Override
    int getId();

    @Override
    String getTitle();

    String getIsbn();

    Date getReleaseDate();

    String getPublisher();

    String getAuthor();

}
