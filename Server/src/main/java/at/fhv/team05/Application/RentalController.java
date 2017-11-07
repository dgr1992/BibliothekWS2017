package at.fhv.team05.Application;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;

import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.domain.Rental;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.persistence.RepositoryFactory;

public class RentalController extends BaseController<Rental, RentalDTO> {

    public RentalController(Class<Rental> rentalClass) {
        super(rentalClass);
    }

    /**
     * From the rentalDTO the original copy and customer object will be obtained.
     * A new rental object will be created from the data of the DTO and added to the copy object.
     * Copy will be set to rent to the specified customer.
     * @param copiesToRent
     * @return
     */
    public static boolean rentCopies(LinkedList<RentalDTO> copiesToRent) {
        for (RentalDTO rentalDTO : copiesToRent) {
            try {
                //Get the original copy object
                CopyDTO copyDTO = rentalDTO.getCopy();
                Copy copy = CopyController.getInstance().getCopyFor(copyDTO);

                //Get the original customer object
                CustomerDTO customerDTO = rentalDTO.getCustomer();
                Customer customer = CustomerController.getInstance().getCustomerFor(customerDTO);

                //Create the domain rental object and fill it with the data from the DTO
                Rental rental = new Rental();
                rental.setCustomer(customer);
                rental.setDeadline(rentalDTO.getDeadline());
                rental.setExtendCounter(rentalDTO.getExtendCounter());
                rental.setPickupDate(rentalDTO.getPickupDate());
                rental.setReturnDate(rentalDTO.getPickupDate());
                rental.setCopy(copy);

                //Add the rental to the copy
                copy.setRentalId(rental);

                //Save copy --> rental will be saved automatically
                RepositoryFactory.getRepositoryInstance(Copy.class).save(copy);
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected RentalDTO createDTO(Rental object) {
        return new RentalDTO(object);
    }

    @Override
    protected boolean compareInput(Rental object, RentalDTO rentalDTO) {
        return false;
    }

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
}
