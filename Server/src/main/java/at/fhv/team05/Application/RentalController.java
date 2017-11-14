package at.fhv.team05.Application;

import at.fhv.team05.ResultDTO;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.domain.Rental;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.CustomerRentalDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.persistence.RepositoryFactory;

import java.util.Calendar;
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

    public ResultDTO<Boolean> rentCopy(RentalDTO copieToRent) {
        try {
            //the ResultDTO
            ResultDTO<Boolean> result = new ResultDTO<>(true, null);
            //Get the original copy object
            CopyDTO copyDTO = copieToRent.getCopy();
            Copy copy = _controllerFacade.getDomainCopy(copyDTO);

            if (copy.getRental() != null) {
                result.setDto(false);
                result.setException(new Exception("Copy is rented."));
                return result;
            }

            //Get the original customer object
            CustomerDTO customerDTO = copieToRent.getCustomer();
            Customer customer = _controllerFacade.getDomainCustomer(customerDTO);

            //Check if customer has paid
            if (customer.getPaymentDate() == null) {
                //Customer needs to pay
                result.setDto(false);
                result.setException(new Exception("Customer needs to pay"));
                return result;
            }
            Calendar currenttime = Calendar.getInstance();
            Calendar expireDate = Calendar.getInstance();
            expireDate.setTime(customer.getPaymentDate());
            expireDate.add(Calendar.YEAR, 1);
            //Check if the customer needs to extend
            if (expireDate.before(currenttime)) {
                //Customer needs to extend
                result.setDto(false);
                result.setException(new Exception("Customer needs to extend his subscription."));
                return result;
            }

            //Create the domain rental object and fill it with the data from the DTO
            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setDeadline(copieToRent.getDeadline());
            rental.setExtendCounter(copieToRent.getExtendCounter());
            rental.setPickupDate(copieToRent.getPickupDate());
            rental.setReturnDate(copieToRent.getReturnDate());
            rental.setCopy(copy);

            //Add the rental to the copy
            copy.setRentalId(rental);

            //set copy status to rented
            copy.setCopyStatus("rented");

            //Save copy --> rental will be saved automatically
            RepositoryFactory.getRepositoryInstance(Copy.class).save(copy);
            return result;
        } catch (Exception ex) {
            return new ResultDTO<>(false, ex);
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


    public LinkedList<CopyDTO> copiesRentedBy(CustomerDTO customerDTO) {

        return null;
    }

    @Override
    protected RentalDTO createDTO(Rental object) {
        return new RentalDTO(object);
    }

    @Override
    protected boolean compareInput(Rental object, RentalDTO rentalDTO) {
        return false;
    }

    public CustomerRentalDTO getRentalsFor(CustomerDTO customerDTO) {
        CustomerRentalDTO customersRentals = new CustomerRentalDTO();

        for (RentalDTO rental : _mapDomainToDto.values()) {
            //If the copy was rent by the customer check if it is currently rented or a old rental
            //TODO rental.getCopy.getRental must be fixed
            if (rental.getCustomer().equals(customerDTO)) {
                if (rental.getCopy().getCopyStatus().equals("rented")) {
                    //If it is the same the copy is currently rented
                    customersRentals.addToCurrent(rental);
                } else {
                    //Was rented in the past
                    customersRentals.addToHistory(rental);
                }
            }
        }

        return customersRentals;
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
