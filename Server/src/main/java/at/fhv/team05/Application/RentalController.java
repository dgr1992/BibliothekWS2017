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

/**
 * Created by Michelle on 02.11.2017.
 */
public class RentalController {

    /**
     * 
     * @param copiesToRent
     * @return
     */
    public static boolean rentCopies(LinkedList<RentalDTO> copiesToRent){
        for (RentalDTO rentalDTO : copiesToRent ) {
            try {
                CopyDTO copyDTO = rentalDTO.getCopy();
                CustomerDTO customerDTO = rentalDTO.getCustomer();

                Copy copy = CopyController.getInstance().getCopyFor(copyDTO);
                Customer customer = CustomerController.getInstance().getCustomerFor(customerDTO);

                //Create the domain rental object and fill it with the data
                Rental rental = new Rental();
                rental.setCustomer(customer);
                rental.setDeadline(rentalDTO.getDeadline());
                rental.setExtendCounter(rentalDTO.getExtendCounter());
                rental.setPickupDate(rentalDTO.getPickupDate());
                rental.setReturnDate(rentalDTO.getPickupDate());
                rental.setCopy(copy);

                copy.setRentalId(rental);

                //Save copy --> rental will be saved automatically
                RepositoryFactory.getRepositoryInstance(Copy.class).save(copy);
            } catch (Exception ex){

            }
        }
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
