package at.fhv.team05.Application;


import at.fhv.team05.ResultDTO;
import at.fhv.team05.Utility.StringUtilities;
import at.fhv.team05.domain.Customer;
import at.fhv.team05.dtos.CustomerDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        return (customer.getCustomerId() == customerDTO.getCustomerId() || customerDTO.getCustomerId() == -1)
                && StringUtilities.containsIgnoreCase(customer.getFirstName(), customerDTO.getFirstName())
                && StringUtilities.containsIgnoreCase(customer.getLastName(), customerDTO.getLastName());

    }

    @Override
    protected CustomerDTO createDTO(Customer customer) {
        return new CustomerDTO(customer);
    }

    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customerDTO) {

        Date paymentDate = customerDTO.getPaymentDate();
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        Customer customer = getDomain(customerDTO);
        Date validUntil = null;

        if (paymentDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(paymentDate);
            c.add(Calendar.YEAR, 1);
            validUntil = new Date(c.getTimeInMillis());
        }

        if (paymentDate == null) {
            customer.setPaymentDate(today);
        } else if (validUntil != null && validUntil.before(today)) {
            customer.setPaymentDate(today);
        } else {
            long daysToAdd = ChronoUnit.DAYS.between(LocalDate.parse(today.toString()), LocalDate.parse(validUntil.toString()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(paymentDate);
            calendar.add(Calendar.DATE, Math.toIntExact(daysToAdd));

            Date newPaymentDate = new Date(calendar.getTimeInMillis());
            customer.setPaymentDate(newPaymentDate);
        }
        CustomerDTO newCustomer = new CustomerDTO(customer);
        save(customer);

        return new ResultDTO<>(newCustomer, null);
    }
}
