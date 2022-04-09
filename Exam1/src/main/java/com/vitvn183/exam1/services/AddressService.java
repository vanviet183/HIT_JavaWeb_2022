package com.vitvn183.exam1.services;

import com.vitvn183.exam1.dao.Address;
import com.vitvn183.exam1.dto.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public List<Address> getAll();

    public Address getById(Long id);

    public List<Address> getByDarlingId(Long darlingId);

    public Address addNew(Long darlingId, AddressDto addressDto) throws Exception;

    public Address update(Long darlingId, Long id, AddressDto addressDto) throws Exception;

    public void delete(Long darlingId, Long id);
}
