package com.vitvn183.exam1.repositories;

import com.vitvn183.exam1.dao.Address;
import com.vitvn183.exam1.dao.Darling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressByDarlingId(Long darlingId);

}
