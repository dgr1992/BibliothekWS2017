package at.fhv.team05.server.Application;


import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.server.Utility.StringUtilities;
import at.fhv.team05.server.domain.Customer;
import at.fhv.team05.library.dtos.CustomerDTO;

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

    /**
     * @param customer
     * @param customerDTO
     * @return true if the customer names or ids match
     */
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

    /**
     * Method for extending the subscription for given customer
     * @param customerDTO Customer for whom the subscription should be extended.
     * @return ResultDTO with CustomerDTO with new payment date.
     */
    public ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customerDTO) {

        Date paymentDate = customerDTO.getPaymentDate();
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        Customer customer = getDomain(customerDTO);
        Date validUntil = null;

        //Calculate new payment date if customer has already payed.
        if (paymentDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(paymentDate);
            c.add(Calendar.YEAR, 1);
            validUntil = new Date(c.getTimeInMillis());
        }

        //Set today as payment date if customer has never payed until now.
        if (paymentDate == null) {
            customer.setPaymentDate(today);

        //Set new payment date if customer has already payed but subscription has expired.
        } else if (validUntil.before(today)) {
            customer.setPaymentDate(today);

        //Set new payment date if customer has already payed but subscription has not expired yet.
        } else {
            //Calculate how many days it would take until the subscription expires.
            long daysToAdd = ChronoUnit.DAYS.between(LocalDate.parse(today.toString()), LocalDate.parse(validUntil.toString()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(paymentDate);

            //New payment date is the payment date + the days left until the subscription would expire.
            calendar.add(Calendar.DATE, Math.toIntExact(daysToAdd));
            Date newPaymentDate = new Date(calendar.getTimeInMillis());
            customer.setPaymentDate(newPaymentDate);
        }
        CustomerDTO newCustomer = new CustomerDTO(customer);
        save(customer);

        return new ResultDTO<>(newCustomer, null);
    }
}
