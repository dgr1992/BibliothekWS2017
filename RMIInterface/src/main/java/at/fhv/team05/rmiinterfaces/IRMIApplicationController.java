package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.Enum.MediaLoanPeriod;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIApplicationController extends Remote {
    ResultListDTO<BookDTO> searchForBook(BookDTO book) throws RemoteException;

    ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException;

    ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO) throws RemoteException;

    ResultDTO<Boolean> rentMedium(RentalDTO rental) throws RemoteException;

    ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException;

    ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) throws RemoteException;

    ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer) throws RemoteException;

    ResultDTO<BookDTO> searchBookById(int mediumId) throws RemoteException;

    ResultDTO<DvdDTO> searchDvdById(int mediumId) throws RemoteException;

    ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) throws RemoteException;

    ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) throws RemoteException;

    boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException;

    ResultDTO<Boolean> reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException;

    ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO) throws RemoteException;

    ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO) throws RemoteException;

    ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium) throws RemoteException;

    void logoutUser() throws RemoteException;

    String getKey() throws RemoteException;

    ResultDTO<ConfigurationDataDTO> getLoanPeriodFor(MediaLoanPeriod mediaLoanPeriod) throws RemoteException;

    ResultDTO<Boolean> sendMessage(String messageText) throws RemoteException;

    ResultDTO<MessageDTO> receiveMessage() throws RemoteException;
}
