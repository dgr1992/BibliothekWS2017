package at.fhv.team05.dtos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IDvd {

    int getId();

    String getTitle();

    String getAsin();

    Date getReleaseDate();

    ICategory getCategory();

    String getPublisher();

    String getDirector();

}
