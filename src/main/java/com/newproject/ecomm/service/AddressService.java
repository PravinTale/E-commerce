package com.newproject.ecomm.service;

import com.newproject.ecomm.model.Address;

import java.util.List;

public interface AddressService {
    public Address addAddress(Address address);
    public Address updateAddress(Address address);
    public Address removeAddress(Integer Id);
    public Address viewAddress(Integer id);
    public List<Address> viewAllAddresses();
}
