package com.newproject.ecomm.controller;


import com.newproject.ecomm.model.Address;
import com.newproject.ecomm.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @PostMapping("/")
    public ResponseEntity<Address> saveAddressHandler(@Valid @RequestBody Address address){
        Address addResponse = addressService.addAddress(address);
        return new ResponseEntity<>(addResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Address> updateAddressHandler(@Valid @RequestBody Address address){
        Address addResp = addressService.updateAddress(address);
        return new ResponseEntity<>(addResp,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Address> deleteAddressHandler(@PathVariable("id") Integer addId){
        Address deletedAddress = addressService.removeAddress(addId);
        return new ResponseEntity<Address>(deletedAddress,HttpStatus.OK);
    }

    @GetMapping("/getaddress/{id}")
    public ResponseEntity<Address> getAddressHandler( @PathVariable("id") Integer addId){
        Address address = addressService.viewAddress(addId);
        return new ResponseEntity<Address>(address,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public List<Address> getAllAddressHandler(){
        return addressService.viewAllAddresses();
    }


}
