package at.fhv.team05.Application;


import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.Application.medium.DvdController;
import at.fhv.team05.Enum.ReturnCopyResult;
import at.fhv.team05.domain.Address;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.dtos.*;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class ControllerFacade implements IRMIApplicationController {
    private static ControllerFacade mInstance;
    private BookController _bookController;
    private DvdController _dvdController;
    private CustomerController _customerController;
    private CopyController _copyController;
    private AddressController _addressController;
    private RentalController _rentalController;
    private ReservationController _reservationController;

    private ControllerFacade() {
        _bookController = BookController.getInstance();
        _dvdController = DvdController.getInstance();
        _customerController = CustomerController.getInstance();
        _copyController = CopyController.getInstance();
        _addressController = AddressController.getInstance();
        _rentalController = RentalController.getInstance();
        _reservationController = ReservationController.getInstance();
    }

    public static ControllerFacade getInstance() {
        if (mInstance == null) {
            mInstance = new ControllerFacade();
        }
        return mInstance;
    }

    @Override
    public LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException {
        return _bookController.searchFor(book);
    }

    @Override
    public LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException {
        return _dvdController.searchFor(dvd);
    }

    @Override
    public boolean rentMedium(RentalDTO rental) {
        return _rentalController.rentCopy(rental);
    }

    @Override
    public List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        return _customerController.searchFor(customer);
    }

    @Override
    public CopyDTO searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        return _copyController.searchCopyByCopyNumber(copyNumber);
    }

    @Override
    public CustomerDTO extendSubscription(CustomerDTO customer) throws RemoteException {
        return _customerController.extendSubscription(customer);
    }

    @Override
    public BookDTO searchBookById(int mediumId) throws RemoteException {
        return _bookController.searchById(mediumId);
    }

    @Override
    public DvdDTO searchDvdById(int mediumId) throws RemoteException {
        return _dvdController.searchById(mediumId);
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


    @Override
    public List<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) {
        return _copyController.getCopiesByMediumID(mediumDTO);
    }

    @Override
    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException {
        return _reservationController.checkAvailability(mediumDTO);
    }

    @Override
    public void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException {
        _reservationController.reserveMedium(mediumDTO, customerDTO);
    }

    public boolean existsReservationForMedium(int mediumID, String mediumTyp){
        return _reservationController.existsReservationForMedium(mediumID,mediumTyp);
    }

    public ReturnCopyResult returnCopy(CopyDTO copyDTO){
        return _copyController.returnCopy(copyDTO);
    }
}
