package at.fhv.team05.Application;

import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.domain.Rental;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.persistence.RepositoryFactory;

import java.util.LinkedList;

public class RentalController extends BaseController<Rental, RentalDTO> {
    private static RentalController _instance;

    private RentalController() {
        super(Rental.class);
    }

    public static RentalController getInstance() {
        if (_instance == null) {
            _instance = new RentalController();
        }
        return _instance;
    }

    public boolean rentCopy(RentalDTO copieToRent) {
        try {
            //Get the original copy object
            CopyDTO copyDTO = copieToRent.getCopy();
            Copy copy = _controllerFacade.getDomainCopy(copyDTO);

            //Get the original customer object
            CustomerDTO customerDTO = copieToRent.getCustomer();
            Customer customer = _controllerFacade.getDomainCustomer(customerDTO);

            //Create the domain rental object and fill it with the data from the DTO
            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setDeadline(copieToRent.getDeadline());
            rental.setExtendCounter(copieToRent.getExtendCounter());
            rental.setPickupDate(copieToRent.getPickupDate());
            rental.setReturnDate(copieToRent.getPickupDate());
            rental.setCopy(copy);

            //Add the rental to the copy
            copy.setRentalId(rental);

            //Save copy --> rental will be saved automatically
            RepositoryFactory.getRepositoryInstance(Copy.class).save(copy);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * From the rentalDTO the original copy and customer object will be obtained.
     * A new rental object will be created from the data of the DTO and added to the copy object.
     * Copy will be set to rent to the specified customer.
     *
     * @param copiesToRent
     * @return
     */
    public void rentCopies(LinkedList<RentalDTO> copiesToRent) {
        for (RentalDTO rentalDTO : copiesToRent) {
            rentCopy(rentalDTO);
        }
    }

    @Override
    protected RentalDTO createDTO(Rental object) {
        return new RentalDTO(object);
    }

    @Override
    protected boolean compareInput(Rental object, RentalDTO rentalDTO) {
        return false;
    }

    /*
    public static void main(String[] args){
        Customer customer = RepositoryFactory.getRepositoryInstance(Customer.class).getById(1);
        Copy copy = RepositoryFactory.getRepositoryInstance(Copy.class).getById(12);

        Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setCopy(new CopyDTO(copy));
        rentalDTO.setCustomer(new CustomerDTO(customer));
        rentalDTO.setPickupDate(sqldate);

        currenttime.add(Calendar.DAY_OF_YEAR, 14);
        sqldate = new Date((currenttime.getTime()).getTime());
        rentalDTO.setReturnDate(sqldate);

        currenttime.add(Calendar.DAY_OF_YEAR, 7);
        sqldate = new Date((currenttime.getTime()).getTime());
        rentalDTO.setDeadline(sqldate);

        LinkedList<RentalDTO> rentalDTOs = new LinkedList<>();
        rentalDTOs.add(rentalDTO);

        rentCopies(rentalDTOs);
    }
    */
}
