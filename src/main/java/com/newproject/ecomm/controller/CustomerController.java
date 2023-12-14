package com.newproject.ecomm.controller;

import com.newproject.ecomm.model.Address;
import com.newproject.ecomm.model.Customer;
import com.newproject.ecomm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
;

    @PostMapping("/")
    public ResponseEntity<Customer> saveCustomerHandler(@Valid @RequestBody Customer customer) {
        Customer cust = customerService.addCustomer(customer);
        return new ResponseEntity<>(cust, HttpStatus.ACCEPTED);
    }


    @PutMapping("/updateCustomer")
    public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer) {
        Customer cust = customerService.updateCustomer(customer);
        return new ResponseEntity<>(cust,HttpStatus.OK);
    }

    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<Customer> getCustomerHandler(@PathVariable Integer customerId) {
        Customer cust = customerService.viewCustomer(customerId);
        return new ResponseEntity<>(cust,HttpStatus.OK);

    }


    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<Customer> deleteCustomerDetailsHandler(@Valid @RequestBody Customer customer) {

       Customer cust =customerService.removeCustomer(customer);
        return new ResponseEntity<>(cust,HttpStatus.ACCEPTED);
    }

    @PutMapping("/addAddress/{customerId}")
    public ResponseEntity<Customer> addAddressCustomerHandler(@Valid @RequestBody Address address, @PathVariable Integer customerId) {
        Customer cust = customerService.addAddress(address,customerId);
        return new ResponseEntity<>(cust,HttpStatus.OK);

    }

}
