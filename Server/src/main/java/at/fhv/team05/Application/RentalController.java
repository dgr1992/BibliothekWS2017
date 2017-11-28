package at.fhv.team05.Application;

import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.domain.Rental;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.CustomerRentalDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.dtos.ReservationDTO;

import java.sql.Date;
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

    /**
     * Allocates a copy to a customer as rented. Before renting it will be checked if the
     * copy is allready rented, if the customer needs to pay the fee or if the customer needs to extend the
     * subscription.
     * @param copieToRent
     * @return ResultDTO: if exception is null the boolean value decides if rent was successful
     */
    public ResultDTO<Boolean> rentCopy(RentalDTO copieToRent) {
        try {
            //the ResultDTO
            ResultDTO<Boolean> result = new ResultDTO<>(true, null);

            //Get the original copy object
            CopyDTO copyDTO = copieToRent.getCopy();
            Copy copy = _controllerFacade.getDomainCopy(copyDTO);

            //Check if the copy is a present object
            if(copy.getCopyStatus().compareToIgnoreCase("present") == 0){
                result.setDto(false);
                result.setException(new Exception("Present copies cannot be rented"));
                return result;
            }

            //Check if there is a reservation for the medium
            if(_controllerFacade.existsReservationForMedium(copy.getMediumId(),copy.getMediaType())){
                //Check if the person selected is the person who is waiting longest
                ResultListDTO<ReservationDTO> resultList = _controllerFacade.getReservationsByIdAndMediumType(copieToRent.getCopy().getMediumId(),copieToRent.getCopy().getMediaType());

                //Get the oldest reservation
                ReservationDTO longestWaitReservation = null;
                for (ReservationDTO reservation: resultList.getListDTO()) {
                    if(longestWaitReservation == null || longestWaitReservation.getReservationDate().after(reservation.getReservationDate())){
                        longestWaitReservation = reservation;
                    }
                }

                //If current customer is the customer from the reservation everything is ok otherwise exception
                if(longestWaitReservation.getCustomer().getCustomerId() != copieToRent.getCustomer().getCustomerId()){
                    result.setDto(false);
                    result.setException(new Exception("Medium is reserved for customer " + longestWaitReservation.getCustomer().getCustomerId() + " " + longestWaitReservation.getCustomer().getFirstName() + " " + longestWaitReservation.getCustomer().getLastName()));
                    return result;
                }

                //Remove reservation
                _controllerFacade.removeReservation(longestWaitReservation);
            }

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
            copy.setRental(rental);

            //set copy status to rented
            copy.setCopyStatus("rented");

            save(rental);
            //Save copy --> rental will be saved automatically
            CopyController.getInstance().save(copy);

            return result;
        } catch (Exception ex) {
            _log.error(ex);
            return new ResultDTO<Boolean>(false, new Exception("Something went wrong. Contact the administrator."));
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

    /**
     * Compare if the object and dto are the same
     * @param object
     * @param rentalDTO
     * @return
     */
    @Override
    protected boolean compareInput(Rental object, RentalDTO rentalDTO) {
        boolean mediumMatch = object.getCopy().getMediumId() == rentalDTO.getCopy().getMediumId();
        boolean copyMatch = object.getCopy().getCopyNumber() == rentalDTO.getCopy().getCopyNumber();
        boolean customerMatch = object.getCustomer().getCustomerId() == rentalDTO.getCustomer().getCustomerId();
        boolean rentIDMatch = object.getId() == rentalDTO.getId();

        return mediumMatch && copyMatch && customerMatch && rentIDMatch;
    }

    /**
     * Searches for all rentals belonging to the given customer
     * @param customerDTO
     * @return Returns rentals allocated to the given customer as rented
     */
    public ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO) {
        CustomerRentalDTO customersRentals = new CustomerRentalDTO();

        for (RentalDTO rental : _mapDomainToDto.values()) {
            //If the copy was rent by the customer check if it is currently rented or a old rental
            if (rental.getCustomer().equals(customerDTO)) {
                if (rental.getCopy().getRental() != null && rental.getCopy().getRental().equals(rental)) {
                    //If it is the same the copy is currently rented
                    customersRentals.addToCurrent(rental);
                } else {
                    //Was rented in the past
                    customersRentals.addToHistory(rental);
                }
            }
        }

        return new ResultDTO<>(customersRentals, null);
    }

    /**
     * Extends the rent period of a rental. Maximum extend of the rental is two times
     * @param rentalDTO
     * @return Returns true if extend was successful. Returns exception message when medium already was extended two times.
     */
    public ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO) {
        Rental rental = getDomain(rentalDTO);

        if(rental == null){
            return new ResultDTO<>(false, new Exception("Rental not found."));
        }

        //Check if there is a reservation for this medium
        if(_controllerFacade.existsReservationForMedium(rental.getCopy().getMediumId(),rental.getCopy().getMediaType())){
            return new ResultDTO<>(false, new Exception("Extending not possible, medium is reserved."));
        }

        if (rental.getExtendCounter() < 2) {
            Calendar calendar = Calendar.getInstance();
            int loanPeriodExtension = Integer.parseInt(_controllerFacade.getConfigFor("LoanPeriodExtension").getValue());
            calendar.add(Calendar.DATE, loanPeriodExtension);
            rental.setDeadline((new Date(calendar.getTimeInMillis())));
            rental.setExtendCounter(rental.getExtendCounter() + 1);
            save(rental);
            return new ResultDTO<>(true, null);
        } else {
            return new ResultDTO<>(false, new Exception("Medium was already extended two times."));
        }
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
