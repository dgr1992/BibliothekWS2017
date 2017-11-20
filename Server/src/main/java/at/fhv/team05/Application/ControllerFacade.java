package at.fhv.team05.Application;


import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.Application.medium.DvdController;
import at.fhv.team05.ObjectInterfaces.IConfigurationData;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.domain.*;
import at.fhv.team05.dtos.*;
import at.fhv.team05.persistence.RepositoryFactory;

import java.util.HashMap;

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

    private HashMap<String, Right> _rights;

    private ControllerFacade() {
        //wird vorerst nicht benötigt
        //_configurationDataController = ConfigurationDataController.getInstance();

        _rights = new HashMap<>();
        RepositoryFactory.getRepositoryInstance(Right.class).list().forEach(right -> _rights.put(right.getName(), right));
    }

    public static ControllerFacade getInstance() {
        if (mInstance == null) {
            mInstance = new ControllerFacade();
        }
        return mInstance;
    }


    public ResultListDTO<BookDTO> searchForBook(BookDTO book) {
        if (_bookController == null) {
            _bookController = BookController.getInstance();
        }
        return _bookController.searchFor(book);
    }


    public ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd) {
        if (_dvdController == null) {
            _dvdController = DvdController.getInstance();
        }
        return _dvdController.searchFor(dvd);
    }


    public ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer) {
        if (_customerController == null) {
            _customerController = CustomerController.getInstance();
        }
        return _customerController.searchFor(customer);
    }


    public ResultDTO<BookDTO> searchBookById(int mediumId) {
        if (_bookController == null) {
            _bookController = BookController.getInstance();
        }
        return _bookController.searchById(mediumId);
    }


    public ResultDTO<DvdDTO> searchDvdById(int mediumId) {
        if (_dvdController == null) {
            _dvdController = DvdController.getInstance();
        }
        return _dvdController.searchById(mediumId);
    }


    public ResultListDTO<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) {
        if (_copyController == null) {
            _copyController = CopyController.getInstance();
        }
        return _copyController.getCopiesByMediumID(mediumDTO);
    }


    public ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) {
        if (_copyController == null) {
            _copyController = CopyController.getInstance();
        }
        return _copyController.searchCopyByCopyNumber(copyNumber);
    }


    public ResultDTO<Boolean> rentMedium(RentalDTO rental) {
        if (_rentalController == null) {
            _rentalController = RentalController.getInstance();
        }
        return _rentalController.rentCopy(rental);
    }


    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer) {
        if (_customerController == null) {
            _customerController = CustomerController.getInstance();
        }
        return _customerController.extendSubscription(customer);
    }


    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) {
        if (_reservationController == null) {
            _reservationController = ReservationController.getInstance();
        }
        return _reservationController.checkAvailability(mediumDTO);
    }


    public ResultDTO<Boolean> reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) {
        if (_reservationController == null) {
            _reservationController = ReservationController.getInstance();
        }
        return _reservationController.reserveMedium(mediumDTO, customerDTO);
    }


    public ResultDTO<Boolean> authenticateUser(UserAccountDTO accountDTO, String key) {
        if (_ldapController == null) {
            _ldapController = LdapController.getInstance();
        }
        return _ldapController.authenticateUser(accountDTO, key);
    }


    public ResultDTO<CustomerRentalDTO> getRentalsFor(CustomerDTO customerDTO) {
        if (_rentalController == null) {
            _rentalController = RentalController.getInstance();
        }
        return _rentalController.getRentalsFor(customerDTO);
    }

    public ResultDTO<Boolean> extendRentedMedium(RentalDTO rentalDTO) {
        if (_rentalController == null) {
            _rentalController = RentalController.getInstance();
        }
        return _rentalController.extendRentedMedium(rentalDTO);
    }

    public ResultListDTO<ReservationDTO> getReservationsByMedium(IMediumDTO medium) {
        if (_reservationController == null) {
            _reservationController = ReservationController.getInstance();
        }
        return _reservationController.getReservationsByMedium(medium);
    }

    public boolean existsReservationForMedium(int mediumID, String mediumTyp) {
        if (_reservationController == null) {
            _reservationController = ReservationController.getInstance();
        }
        return _reservationController.existsReservationForMedium(mediumID, mediumTyp);
    }

    public ResultDTO<Boolean> returnCopy(CopyDTO copyDTO) {
        if (_copyController == null) {
            _copyController = CopyController.getInstance();
        }
        return _copyController.returnCopy(copyDTO);
    }

    public Address getDomainAddress(AddressDTO addressDTO) {
        if (_addressController == null) {
            _addressController = AddressController.getInstance();
        }
        return _addressController.getDomain(addressDTO);
    }

    public Copy getDomainCopy(CopyDTO copyDTO) {
        if (_copyController == null) {
            _copyController = CopyController.getInstance();
        }
        return _copyController.getDomain(copyDTO);
    }

    public Customer getDomainCustomer(CustomerDTO customerDTO) {
        if (_customerController == null) {
            _customerController = CustomerController.getInstance();
        }
        return _customerController.getDomain(customerDTO);
    }

    public IConfigurationData getConfigFor(String name) {
        return _configurationDataController.getConfigFor(name);
    }

    public ResultDTO<ConfigurationDataDTO> getConfigDTOFor(String name) {
        return _configurationDataController.getConfigDTOFor(name);
    }

    public UserAccount getDomainAccount(UserAccountDTO accountDTO) {
        if (_ldapController == null) {
            _ldapController = LdapController.getInstance();
        }
        return _ldapController.getDomain(accountDTO);
    }

    public Right getRight(String rightName) {
        return _rights.get(rightName);
    }
}
