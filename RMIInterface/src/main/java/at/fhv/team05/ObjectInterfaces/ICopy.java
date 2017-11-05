package at.fhv.team05.ObjectInterfaces;

/**
 * Created by Daniel on 05.11.2017.
 */
public interface ICopy {
    int getId();
    int getMediumId();
    int getCopyNumber();
    String getMediaType();
    IRental getRental();
}
