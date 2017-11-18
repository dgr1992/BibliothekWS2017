package at.fhv.team05.Application;


import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.Application.medium.DvdController;
import at.fhv.team05.ObjectInterfaces.IConfigurationData;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.domain.Address;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.dtos.*;

import java.rmi.RemoteException;

public class ControllerFacade {
    private static ControllerFacade mInstance;
    private BookController _bookController;
    private DvdController _dvdController;
    private CustomerController _customerController;
    private CopyController _copyController;
    private AddressController _addressController;
    private RentalController _rentalController;
    private ReservationController _reservationController;
    private LdapController _ldapController;
    private ConfigurationDataController _configurationDataController;

    private ControllerFacade() {
        _bookController = BookController.getInstance();
        _dvdController = DvdController.getInstance();
        _customerController = CustomerController.getInstance();
        _copyController = CopyController.getInstance();
        _addressController = AddressController.getInstance();
        _rentalController = RentalController.getInstance();
        _reservationController = ReservationController.getInstance();
        _ldapController = LdapController.getInstance();
        _configurationDataController = ConfigurationDataController.getInstance();
    }

    public static ControllerFacade getInstance() {
        if (mInstance == null) {
            mInstance = new ControllerFacade();
        }
        return mInstance;
    }


    public ResultListDTO<BookDTO> searchForBook(BookDTO book) {
        return _bookController.searchFor(book);
    }


    public ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd) {
        return _dvdController.searchFor(dvd);
    }


    public ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer) {
        return _customerController.searchFor(customer);
    }


    public ResultDTO<BookDTO> searchBookById(int mediumId) {
        return _bookController.searchById(mediumId);
    }


    public ResultDTO<DvdDTO> searchDvdById(int mediumId) {
        return _dvdController.searchById(mediumId);
    }


    public ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) {
        return _copyController.getCopiesByMediumID(mediumDTO);
    }


    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) {
        return _copyController.searchCopyByCopyNumber(copyNumber);
    }


    public ResultDTO<Boolean> rentMedium(RentalDTO rental) {
        return _rentalController.rentCopy(rental);
    }


    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer) {
        return _customerController.extendSubscription(customer);
    }


    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) {
        return _reservationController.checkAvailability(mediumDTO);
    }


    public void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) {
        _reservationController.reserveMedium(mediumDTO, customerDTO);
    }


    public ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO, String key) {
        return _ldapController.authenticateUser(accountDTO, key);
    }


    public ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO) {
        return _rentalController.getRentalsFor(customerDTO);
    }


    public ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO) {
        return _rentalController.extendRentedMedium(rentalDTO);
    }

    public ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium)  {
        return _reservationController.getReservationsByMedium(medium);
    }

    public boolean existsReservationForMedium(int mediumID, String mediumTyp) {
        return _reservationController.existsReservationForMedium(mediumID, mediumTyp);
    }

    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) {
        return _copyController.returnCopy(copyDTO);
    }

    public Address getDomainAddress(AddressDTO addressDTO) {
        return _addressController.getDomain(addressDTO);
    }

    public Copy getDomainCopy(CopyDTO copyDTO) {
        return _copyController.getDomain(copyDTO);
    }

    public Customer getDomainCustomer(CustomerDTO customerDTO) {
        return _customerController.getDomain(customerDTO);
    }

    public IConfigurationData getConfigFor(String name){
        return _configurationDataController.getConfigFor(name);
    }

    public ResultDTO<ConfigurationDataDTO> getConfigDTOFor(String name){
        return _configurationDataController.getConfigDTOFor(name);
    }
}
