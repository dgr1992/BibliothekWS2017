package at.fhv.team05.dtos;

import java.util.Date;

/**
 * Created by Michelle on 30.10.2017.
 */
public interface IBook {

    int getID();
    String getTtile();
    String getIsbn();
    Date getReleaseDate();
    ICategory getCategory();
    String getPublisher();
    String getAuthor();

}
