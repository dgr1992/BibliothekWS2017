package at.fhv.team05.dtos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IBook {

    int getId();

    String getTitle();

    String getIsbn();

    Date getReleaseDate();

    ICategory getCategory();

    String getPublisher();

    String getAuthor();

}
