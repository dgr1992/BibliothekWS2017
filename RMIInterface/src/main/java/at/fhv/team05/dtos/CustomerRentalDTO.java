package at.fhv.team05.dtos;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by daniel on 13.11.17.
 */
public class CustomerRentalDTO implements Serializable{
    private LinkedList<RentalDTO> _current;
    private LinkedList<RentalDTO> _history;

    public CustomerRentalDTO(){
        _current = new LinkedList<>();
        _history = new LinkedList<>();
    }

    public boolean addToCurrent(RentalDTO rentalDTO){
        return _current.add(rentalDTO);
    }

    public boolean addToHistory(RentalDTO rentalDTO){
        return _history.add(rentalDTO);
    }

    public LinkedList<RentalDTO> getCurrent(){
        return new LinkedList<>(_current);
    }

    public LinkedList<RentalDTO> getHistory(){
        return new LinkedList<>(_history);
    }
}
