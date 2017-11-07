package at.fhv.team05.ObjectInterfaces;

import at.fhv.team05.dtos.IMediumDTO;

import java.util.Date;

public interface IBook extends IMediumDTO {

    int getId();

    String getTitle();

    String getIsbn();

    Date getReleaseDate();

    ICategory getCategory();

    String getPublisher();

    String getAuthor();

}