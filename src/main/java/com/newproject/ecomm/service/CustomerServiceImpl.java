package com.newproject.ecomm.service;

import com.newproject.ecomm.exceptions.NoCustomerFoundException;
import com.newproject.ecomm.exceptions.UserAlreadyExistsException;
import com.newproject.ecomm.exceptions.UserNotLoggedInException;
import com.newproject.ecomm.model.Address;
import com.newproject.ecomm.model.Customer;
import com.newproject.ecomm.model.User;
import com.newproject.ecomm.repository.AddressDao;
import com.newproject.ecomm.repository.CurrentUserSessionDao;
import com.newproject.ecomm.repository.CustomerDao;
import com.newproject.ecomm.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements  CustomerService {
    @Autowired
    private CustomerDao customerRepo;
    @Autowired
    private UserDao userRepo;
    @Autowired
    private CurrentUserSessionDao currentSessionRepo;
    @Autowired
    private AddressDao addressRepo;

    @Override
    public Customer addCustomer(Customer customer) {
        String mobile = customer.getMobileNo();
        User user = userRepo.findByMobile(mobile);
        Customer existingCustomer = customerRepo.findCustomerByMobileNo(mobile);

        if (existingCustomer != null) {
            throw new UserAlreadyExistsException("Customer already present with mobile number " + mobile);
        }

        if (user != null) {
            Integer userId = user.getUserId();
            String currentUserId = currentSessionRepo.findUserById(userId);

            if (currentUserId != null) {
                if (!customer.getAddressList().isEmpty()) {
                    customer.getAddressList().forEach(addressRepo::save);
                }
                return customerRepo.save(customer);
            } else {
                throw new UserNotLoggedInException("Please login");
            }
        } else {
            throw new UserNotLoggedInException("Please login");
        }
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> cust = customerRepo.findById(customer.getCustId());
        Customer customerIspresent = customerRepo.findCustomerByMobileNo(customer.getMobileNo());
//		if(customerIspresent==null) throw new UserAlreadyExists("already present with this mobile number");
        if(customerIspresent!=null) {
            if(cust.isPresent()) {

                Customer optCustomer= cust.get();
                List<Address> addresses= optCustomer.getAddressList();
                if(addresses.size()!=0) {
                    for(Address address:addresses) {
//						Integer addressId= address.getAddressId();
                        addressRepo.save(address);
                    }
                }
                return  customerRepo.save(customer);
            }
            else
                throw new NoCustomerFoundException("Invalid Customer input.");
        }
        else
            throw new NoCustomerFoundException("You Can't change Registered Mobile Number");
    }

    @Override
    public Customer removeCustomer(Customer customer) {
        Optional<Customer> cust = customerRepo.findById(customer.getCustId());
        if(!cust.isEmpty()){
            Customer optCustomer= cust.get();
            customerRepo.delete(optCustomer);
            return customer;
        }
        else {
            throw new NoCustomerFoundException("No customer found for Id:"+ customer.getCustId());
        }
    }

    @Override
    public Customer viewCustomer(Integer id) {
         Optional<Customer> cust = customerRepo.findById(id);
         Customer optCust = cust.get();
        return optCust;
    }

    @Override
    public List<Customer> viewAllCustomerByCity(String city) {

        List<Address> addresses= addressRepo.findByCity(city);
        if (!addresses.isEmpty()) {
            // Extract customer IDs from addresses
            List<Integer> customerIds = addresses.stream()
                    .map(address -> address.getCustomer().getCustId())
                    .collect(Collectors.toList());

            // Fetch customers based on customer IDs
            List<Customer> customers = customerRepo.findAllById(customerIds);
            return customers;
        } else {
            // No addresses found in the given city
            return Collections.emptyList();
        }
    }

    @Override
    public Customer addAddress(Address address, Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.getAddressList().add(address);
            customerRepo.save(customer);
            return customer;
        } else {
            // Handle the case when the customer with the given ID is not found
            throw new NoCustomerFoundException("Customer not found with ID: " + customerId);
        }
    }
}
