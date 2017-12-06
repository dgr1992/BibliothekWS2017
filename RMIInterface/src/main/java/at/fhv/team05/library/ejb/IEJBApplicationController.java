package at.fhv.team05.library.ejb;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.ejb.Remote;

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

/**
 * Created by daniel on 06.12.17.
 */
@Remote
public interface IEJBApplicationController extends Serializable {
    ResultListDTO<BookDTO> searchForBook(BookDTO book);

    ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd) ;

    ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO)  ;

    ResultDTO<Boolean> rentMedium(RentalDTO rental)  ;

    ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer)  ;

    ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber)  ;

    ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer)  ;

    ResultDTO<BookDTO> searchBookById(int mediumId)  ;

    ResultDTO<DvdDTO> searchDvdById(int mediumId)  ;

    ResultDTO<Boolean> returnCopy(CopyDTO copyDTO)  ;

    ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO)  ;

    boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO)  ;

    ResultDTO<Boolean> reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO)  ;

    ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO)  ;

    ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO)  ;

    ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium)  ;

    void logoutUser()  ;

    String getKey()  ;

    ResultDTO<ConfigurationDataDTO> getLoanPeriodFor(MediaLoanPeriod mediaLoanPeriod)  ;

    ResultDTO<Boolean> sendMessage(String messageText)  ;

    ResultDTO<MessageDTO> receiveMessage()  ;
}
