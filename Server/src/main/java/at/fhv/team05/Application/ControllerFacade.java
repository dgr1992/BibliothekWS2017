package at.fhv.team05.Application;


import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.Application.medium.DvdController;
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

    private ControllerFacade() {
        _bookController = BookController.getInstance();
        _dvdController = DvdController.getInstance();
        _customerController = CustomerController.getInstance();
        _copyController = CopyController.getInstance();
        _addressController = AddressController.getInstance();
        _rentalController = RentalController.getInstance();
        _reservationController = ReservationController.getInstance();
        _ldapController = LdapController.getInstance();
    }

    public static ControllerFacade getInstance() {
        if (mInstance == null) {
            mInstance = new ControllerFacade();
        }
        return mInstance;
    }


    public ResultListDTO<BookDTO> searchForBook(BookDTO book) throws RemoteException {
        return _bookController.searchFor(book);
    }


    public ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException {
        return _dvdController.searchFor(dvd);
    }


    public ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        return _customerController.searchFor(customer);
    }


    public ResultDTO<BookDTO> searchBookById(int mediumId) throws RemoteException {
        return _bookController.searchById(mediumId);
    }


    public ResultDTO<DvdDTO> searchDvdById(int mediumId) throws RemoteException {
        return _dvdController.searchById(mediumId);
    }


    public ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) {
        return _copyController.getCopiesByMediumID(mediumDTO);
    }


    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        return _copyController.searchCopyByCopyNumber(copyNumber);
    }


    public ResultDTO<Boolean> rentMedium(RentalDTO rental) {
        return _rentalController.rentCopy(rental);
    }


    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer) throws RemoteException {
        return _customerController.extendSubscription(customer);
    }


    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException {
        return _reservationController.checkAvailability(mediumDTO);
    }


    public void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException {
        _reservationController.reserveMedium(mediumDTO, customerDTO);
    }


    public ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO, String key) throws RemoteException {
        return _ldapController.authenticateUser(accountDTO, key);
    }


    public ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO) throws RemoteException {
        return _rentalController.getRentalsFor(customerDTO);
    }


    public ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO) throws RemoteException {
        return _rentalController.extendRentedMedium(rentalDTO);
    }

    public ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium) throws RemoteException {
        return _reservationController.getReservationsByMedium(medium);
    }

    public boolean existsReservationForMedium(int mediumID, String mediumTyp) {
        return _reservationController.existsReservationForMedium(mediumID, mediumTyp);
    }

    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) {
        return _copyController.returnCopy(copyDTO);
    }

    public Address getDomainAddress(AddressDTO adressDTO) {
        return _addressController.getDomain(adressDTO);
    }

    public Copy getDomainCopy(CopyDTO copyDTO) {
        return _copyController.getDomain(copyDTO);
    }

    public Customer getDomainCustomer(CustomerDTO customerDTO) {
        return _customerController.getDomain(customerDTO);
    }

}
