package at.fhv.team05.library.ObjectInterfaces;

import at.fhv.team05.library.dtos.IMediumDTO;

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
