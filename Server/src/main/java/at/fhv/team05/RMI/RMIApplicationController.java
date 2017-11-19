package at.fhv.team05.RMI;

import at.fhv.team05.Application.ControllerFacade;
import at.fhv.team05.Application.LdapController;
import at.fhv.team05.Enum.MediaLoanPeriod;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.domain.Right;
import at.fhv.team05.domain.UserAccount;
import at.fhv.team05.dtos.*;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIApplicationController extends UnicastRemoteObject implements IRMIApplicationController {
    private ControllerFacade _controllerFacade;
    private final String _key;
    private UserAccount _account = null;
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
        try {
            checkPermission(_controllerFacade.getRight("rentMedium"));
            return _controllerFacade.rentMedium(rental);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("searchCustomer"));
            return _controllerFacade.searchForCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultListDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("searchCopy"));
            return _controllerFacade.searchCopyByCopyNumber(copyNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("extendSubscription"));
            return _controllerFacade.extendSubscription(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<BookDTO> searchBookById(int mediumId) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("searchMedium"));
            return _controllerFacade.searchBookById(mediumId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<DvdDTO> searchDvdById(int mediumId) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("searchMedium"));
            return _controllerFacade.searchDvdById(mediumId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("returnCopy"));
            return _controllerFacade.returnCopy(copyDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("searchCopy"));
            return _controllerFacade.getCopiesByMedium(mediumDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultListDTO<>(null, e);
        }
    }

    @Override
    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException {
        return _controllerFacade.checkAvailabilityOfMedium(mediumDTO);
    }

    @Override
    public void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException {
//TODO client auf ResultDTO umbauen
        //        try {
//            checkPermission(_controllerFacade.getRight("reserveMedium"));
        _controllerFacade.reserveMedium(mediumDTO, customerDTO);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResultDTO<>(null, e);
//        }
    }

    @Override
    public ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("searchRentals"));
            return _controllerFacade.getRentalsFor(customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("extendRentedMedium"));
            return _controllerFacade.extendRentedMedium(rentalDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium) throws RemoteException {
        try {
            checkPermission(_controllerFacade.getRight("searchReservations"));
            return _controllerFacade.getReservationsByMedium(medium);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultListDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO) throws RemoteException {
        ResultDTO<Boolean> tmpDTO = _controllerFacade.authenticateUser(accountDTO, _key);
        //getDTO gibt true, falls login erfolgreich war, ansonsten false
        if (tmpDTO.getDto()) {
            _account = _controllerFacade.getDomainAccount(accountDTO);
        }
        return tmpDTO;
    }

    @Override
    public String getKey() throws RemoteException {
        return _key;
    }

    @Override
    public ResultDTO<ConfigurationDataDTO> getLoanPeriodFor(MediaLoanPeriod mediaLoanPeriod) throws RemoteException {
        ResultDTO<ConfigurationDataDTO> result;
        switch (mediaLoanPeriod) {
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

        return result;
    }

    private void checkPermission(Right right) throws Exception {
        if (_account != null && _account.getRoles() != null && (_account.getRoles().stream().filter(role -> role.getRights().contains(right)).count() > 0)) {
            return;
        }
        throw new Exception("You have no permission for this action.");
    }
}
