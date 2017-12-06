package at.fhv.team05.server.ejb;

import java.rmi.RemoteException;

import javax.ejb.Stateful;

import at.fhv.team05.library.Enum.MediaLoanPeriod;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.ResultListDTO;
import at.fhv.team05.library.dtos.BookDTO;
import at.fhv.team05.library.dtos.ConfigurationDataDTO;
import at.fhv.team05.library.dtos.CopyDTO;
import at.fhv.team05.library.dtos.CustomerDTO;
import at.fhv.team05.library.dtos.CustomerRentalDTO;
import at.fhv.team05.library.dtos.DvdDTO;
import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.library.dtos.MessageDTO;
import at.fhv.team05.library.dtos.RentalDTO;
import at.fhv.team05.library.dtos.ReservationDTO;
import at.fhv.team05.library.dtos.UserAccountDTO;
import at.fhv.team05.library.ejb.IEJBApplicationController;
import at.fhv.team05.server.Application.ControllerFacade;
import at.fhv.team05.server.Application.LdapController;
import at.fhv.team05.server.domain.Right;
import at.fhv.team05.server.domain.UserAccount;

/**
 * Created by daniel on 06.12.17.
 */
@Stateful(name="EJBApplicationController")
public class EJBApplicationController implements IEJBApplicationController {
    private ControllerFacade _controllerFacade;
    private final String _key;
    private UserAccount _account = null;
    private static final int KEY_LENGTH = 32;

    public EJBApplicationController() throws RemoteException {
        _controllerFacade = ControllerFacade.getInstance();
        _key = LdapController.getRandomHexString(KEY_LENGTH);
    }

    @Override
    public ResultListDTO<BookDTO> searchForBook(BookDTO book) {
        return _controllerFacade.searchForBook(book);
    }

    @Override
    public ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd)   {
        return _controllerFacade.searchForDvd(dvd);
    }

    @Override
    public ResultDTO<Boolean> rentMedium(RentalDTO rental)   {
        try {
            checkPermission(_controllerFacade.getRight("rentMedium"));
            return _controllerFacade.rentMedium(rental);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer)   {
        try {
            checkPermission(_controllerFacade.getRight("searchCustomer"));
            return _controllerFacade.searchForCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultListDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber)   {
        try {
            checkPermission(_controllerFacade.getRight("searchCopy"));
            return _controllerFacade.searchCopyByCopyNumber(copyNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer)   {
        try {
            checkPermission(_controllerFacade.getRight("extendSubscription"));
            return _controllerFacade.extendSubscription(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<BookDTO> searchBookById(int mediumId)   {
        try {
            checkPermission(_controllerFacade.getRight("searchMedium"));
            return _controllerFacade.searchBookById(mediumId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<DvdDTO> searchDvdById(int mediumId)   {
        try {
            checkPermission(_controllerFacade.getRight("searchMedium"));
            return _controllerFacade.searchDvdById(mediumId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO)   {
        try {
            checkPermission(_controllerFacade.getRight("returnCopy"));
            return _controllerFacade.returnCopy(copyDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO)   {
        return _controllerFacade.getCopiesByMedium(mediumDTO);
    }

    @Override
    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO)   {
        return _controllerFacade.checkAvailabilityOfMedium(mediumDTO);
    }

    @Override
    public ResultDTO<Boolean> reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO)   {
        try {
            checkPermission(_controllerFacade.getRight("reserveMedium"));
            return _controllerFacade.reserveMedium(mediumDTO, customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(false, e);
        }
    }

    @Override
    public ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO)   {
        try {
            checkPermission(_controllerFacade.getRight("searchRentals"));
            return _controllerFacade.getRentalsFor(customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO)   {
        try {
            checkPermission(_controllerFacade.getRight("extendRentedMedium"));
            return _controllerFacade.extendRentedMedium(rentalDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    @Override
    public ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium)   {
        try {
            checkPermission(_controllerFacade.getRight("searchReservations"));
            return _controllerFacade.getReservationsByMedium(medium);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultListDTO<>(null, e);
        }
    }

    @Override
    public ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO)   {
        ResultDTO<Boolean> tmpDTO = _controllerFacade.authenticateUser(accountDTO, _key);
        //getDTO gibt true, falls login erfolgreich war, ansonsten false
        if (tmpDTO.getDto()) {
            _account = _controllerFacade.getDomainAccount(accountDTO);
        }
        return tmpDTO;
    }

    @Override
    public void logoutUser()   {
        _account = null;
    }

    @Override
    public String getKey()   {
        return _key;
    }

    @Override
    public ResultDTO<ConfigurationDataDTO> getLoanPeriodFor(MediaLoanPeriod mediaLoanPeriod)   {
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

    @Override
    public ResultDTO<Boolean> sendMessage(String messageText)   {
        try {
            checkPermission(_controllerFacade.getRight("messageService"));
            return _controllerFacade.sendMessage(messageText);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(false, e);
        }
    }

    @Override
    public ResultDTO<MessageDTO> receiveMessage()   {
        try {
            checkPermission(_controllerFacade.getRight("messageService"));
            return _controllerFacade.receiveMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO<>(null, e);
        }
    }

    /**
     * This method checks if the user has the needed rights to perform the action.
     *
     * @param right
     * @throws Exception
     */
    private void checkPermission(Right right) throws Exception {
        if (_account != null && _account.getRoles() != null && (_account.getRoles().stream().filter(role -> role.getRights().contains(right)).count() > 0)) {
            return;
        }
        throw new Exception("You have no permission for this action.");
    }
}
