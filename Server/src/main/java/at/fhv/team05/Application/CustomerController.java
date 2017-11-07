package at.fhv.team05.Application;


import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.persistence.RepositoryFactory;

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
        return StringUtilities.containsIgnoreCase(customer.getFirstName(), customerDTO.getFirstName())
                && StringUtilities.containsIgnoreCase(customer.getLastName(), customerDTO.getLastName())
                && (customer.getCustomerId() == customerDTO.getCustomerId());
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
        customer.setAddressId(customerDTO.getAddressId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setPaymentDate(new Date(Calendar.getInstance().getTimeInMillis()));

        _repository.save(customer);
    }

    /**
     * Gets the domain object that the CustomerDTO represents
     * @param customerDTO
     * @return
     */
    public Customer getCustomerFor(CustomerDTO customerDTO) {
        for (Customer customer : RepositoryFactory.getRepositoryInstance(Customer.class).list()) {
            if (customer.getId() == customerDTO.getId()) {
                return customer;
            }
        }
        return null;
    }
}
