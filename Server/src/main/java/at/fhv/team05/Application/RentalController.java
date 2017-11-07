package at.fhv.team05.Application;

import at.fhv.team05.domain.Rental;
import at.fhv.team05.dtos.RentalDTO;

import java.util.LinkedList;

public class RentalController extends BaseController<Rental, RentalDTO> {

    public RentalController(Class<Rental> rentalClass) {
        super(rentalClass);
    }

    public boolean rentCopies(LinkedList<RentalDTO> copiesToRent) {
        return false;
    }

    @Override
    protected RentalDTO createDTO(Rental object) {
        return new RentalDTO(object);
    }

    @Override
    protected boolean compareInput(Rental object, RentalDTO rentalDTO) {
        return false;
    }
}
