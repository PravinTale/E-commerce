package com.newproject.ecomm.repository;

import com.newproject.ecomm.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {
    @Query("select add from Address add where add.addId=?1")
    public Address findByAddessId(Integer addId);
    public List<Address> findByCity(String city);
}
