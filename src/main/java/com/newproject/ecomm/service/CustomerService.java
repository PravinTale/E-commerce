package com.newproject.ecomm.service;

import com.newproject.ecomm.model.Address;
import com.newproject.ecomm.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public Customer removeCustomer(Customer customer);

    public Customer viewCustomer(Integer id);

    public List<Customer> viewAllCustomerByCity(String location);

    public Customer addAddress(Address address, Integer customerId);
}
