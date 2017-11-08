package at.fhv.team05.Application;


import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.dtos.CustomerDTO;

import java.sql.Date;
import java.util.Calendar;

public class CustomerController extends BaseController<Customer, CustomerDTO> {

    private static CustomerController _theInstance;

    private CustomerController() {
        super(Customer.class);
    }

    public static CustomerController getInstance() {
        if (_theInstance == null) {
            _theInstance = new CustomerController();
        }
        return _theInstance;
    }

    @Override
    protected boolean compareInput(Customer customer, CustomerDTO customerDTO) {
        return  StringUtilities.containsIgnoreCase (Integer.toString(customer.getCustomerId()),Integer.toString(customerDTO.getCustomerId()))
                && StringUtilities.containsIgnoreCase(customer.getFirstName(), customerDTO.getFirstName())
                && StringUtilities.containsIgnoreCase(customer.getLastName(), customerDTO.getLastName());

    }

    @Override
    protected CustomerDTO createDTO(Customer customer) {
        return new CustomerDTO(customer);
    }

    public void extendSubscription(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setAddress(_controllerFacade.getDomainAddress(customerDTO.getAddress()));
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setPaymentDate(new Date(Calendar.getInstance().getTimeInMillis()));

        _repository.save(customer);
    }
}
