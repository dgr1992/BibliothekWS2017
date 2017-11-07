package at.fhv.team05.Application;


import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;

import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Daniel on 05.11.2017.
 */
public class CustomerController{

    private Repository<Customer> _repositoryCustomer;
    private HashSet<Customer> _allCustomers;
    private static Class currentClass = new Object() {
    }.getClass().getEnclosingClass();
    private static final Logger log = LogManager.getLogger(currentClass);

    private static CustomerController _theInstance;

    private CustomerController() {
        _repositoryCustomer = RepositoryFactory.getRepositoryInstance(Customer.class);

        _allCustomers = new HashSet<>();
        _allCustomers.addAll(_repositoryCustomer.list());
    }

    public static CustomerController getInstance(){
        if(_theInstance == null){
            _theInstance = new CustomerController();
        }
        return _theInstance;
    }

    public LinkedList<CustomerDTO> searchForCustomer(CustomerDTO customerDTO) {
        LinkedList<CustomerDTO> result = new LinkedList<>();
        for (Customer customer : _allCustomers) {
            if (compareInput(customer, customerDTO)) {
                result.add(createDTO(customer));
            }
        }
        return result;
    }

    private boolean compareInput(Customer customer, CustomerDTO customerDTO){
        return StringUtilities.containsIgnoreCase(customer.getFirstName(), customerDTO.getFirstName())
                && StringUtilities.containsIgnoreCase(customer.getLastName(), customerDTO.getLastName())
                && (customer.getCustomerId() == customerDTO.getCustomerId());
    }

    private CustomerDTO createDTO(Customer customer){
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

        _repositoryCustomer.save(customer);

    }
}
