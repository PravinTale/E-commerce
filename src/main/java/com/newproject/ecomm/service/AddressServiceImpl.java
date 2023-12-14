package com.newproject.ecomm.service;

import com.newproject.ecomm.exceptions.AddressNotFoundException;
import com.newproject.ecomm.model.Address;
import com.newproject.ecomm.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressDao addressRepo;
    @Override
    public Address addAddress(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
       Address add= addressRepo.findByAddessId(address.getAddId());
//        if(add != null){
            return addressRepo.save(address);
//        }
//        else {
//            throw new AddressNotFoundException("Address not found for Id: " + address.getAddId() );
//        }
    }

    @Override
    public Address removeAddress(Integer Id) {
        Address address = addressRepo.findByAddessId(Id);
        if (address != null){
            addressRepo.delete(address);
            return address;
        }
        else {
            throw new AddressNotFoundException("Address does not exists");
        }
    }

    @Override
    public Address viewAddress(Integer id) {
        Address add = addressRepo.findByAddessId(id);

        if (add != null) {
            return add;
        } else {
            throw new AddressNotFoundException("Address does not exists");
        }

    }

    @Override
    public List<Address> viewAllAddresses() {
        List<Address> addresses = addressRepo.findAll();
        if (addresses != null) {
            return addresses;
        } else {
            throw new AddressNotFoundException("Address does not exists");
        }

    }
}
