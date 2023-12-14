package com.newproject.ecomm.repository;

import com.newproject.ecomm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
    public Customer findCustomerByMobileNo(String mobileNo);
}
