package at.fhv.team05.dtos;

import java.util.Date;

public interface IBook {

    int getID();
    String getTitle();
    String getIsbn();
    Date getReleaseDate();
    ICategory getCategory();
    String getPublisher();
    String getAuthor();

}
