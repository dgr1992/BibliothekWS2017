package at.fhv.team05.RMI;

import at.fhv.team05.Application.ControllerFacade;
import at.fhv.team05.Application.LdapController;
import at.fhv.team05.Enum.MediaLoanPeriod;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.*;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIApplicationController extends UnicastRemoteObject implements IRMIApplicationController {
    private ControllerFacade _controllerFacade;
    private final String _key;
    private static final int KEY_LENGTH = 32;

    public RMIApplicationController() throws RemoteException {
        _controllerFacade = ControllerFacade.getInstance();
        _key = LdapController.getRandomHexString(KEY_LENGTH);
    }

    @Override
    public ResultListDTO<BookDTO> searchForBook(BookDTO book) throws RemoteException {
        return _controllerFacade.searchForBook(book);
    }

    @Override
    public ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException {
        return _controllerFacade.searchForDvd(dvd);
    }

    @Override
    public ResultDTO<Boolean> rentMedium(RentalDTO rental) throws RemoteException {
        return _controllerFacade.rentMedium(rental);
    }

    @Override
    public ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        return _controllerFacade.searchForCustomer(customer);
    }

    @Override
    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        return _controllerFacade.searchCopyByCopyNumber(copyNumber);
    }

    @Override
    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer) throws RemoteException {
        return _controllerFacade.extendSubscription(customer);
    }

    @Override
    public ResultDTO<BookDTO> searchBookById(int mediumId) throws RemoteException {
        return _controllerFacade.searchBookById(mediumId);
    }

    @Override
    public ResultDTO<DvdDTO> searchDvdById(int mediumId) throws RemoteException {
        return _controllerFacade.searchDvdById(mediumId);
    }

    @Override
    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) throws RemoteException {
        return _controllerFacade.returnCopy(copyDTO);
    }

    @Override
    public ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) throws RemoteException {
        return _controllerFacade.getCopiesByMedium(mediumDTO);
    }

    @Override
    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException {
        return _controllerFacade.checkAvailabilityOfMedium(mediumDTO);
    }

    @Override
    public void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException {
        _controllerFacade.reserveMedium(mediumDTO, customerDTO);
    }

    @Override
    public ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO) throws RemoteException {
        return _controllerFacade.getRentalsFor(customerDTO);
    }

    @Override
    public ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO) throws RemoteException {
        return _controllerFacade.extendRentedMedium(rentalDTO);
    }

    @Override
    public ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium) throws RemoteException {
        return _controllerFacade.getReservationsByMedium(medium);
    }

    @Override
    public ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO) throws RemoteException {
        return _controllerFacade.authenticateUser(accountDTO, _key);
    }

    @Override
    public String getKey() throws RemoteException {
        return _key;
    }

    @Override
    public ResultDTO<ConfigurationDataDTO> getLoanPeriodFor(MediaLoanPeriod mediaLoanPeriod) throws RemoteException {
        ResultDTO<ConfigurationDataDTO> result;

        switch (mediaLoanPeriod){
            case DVD:
                result = _controllerFacade.getConfigDTOFor("DVDLoanPeriod");
                break;
            case Book:
                result = _controllerFacade.getConfigDTOFor("BookLoanPeriod");
                break;
            default:
                result = new ResultDTO<>(null, new Exception("No loan period for " + mediaLoanPeriod));
                break;
        }

        return  result;
    }


}
