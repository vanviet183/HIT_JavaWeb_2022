package com.vitvn183.exam1.services;

import com.vitvn183.exam1.dao.Address;
import com.vitvn183.exam1.dao.Darling;
import com.vitvn183.exam1.dto.AddressDto;
import com.vitvn183.exam1.exception.NotFoundException;
import com.vitvn183.exam1.repositories.AddressRepository;
import com.vitvn183.exam1.repositories.DarlingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DarlingRepository darlingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addNew(Long darlingId, AddressDto addressDto) {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        if(darling.isEmpty()) {
            throw new NotFoundException("Darling's id not found");
        }
        Address addressNew = new Address();
        createOrUpdate(addressNew, darlingId, addressDto);
        addressNew.setDarling(darling.get());

    }

    @Override
    public void update(Long darlingId, Long id, AddressDto addressDto) {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        checkDarlingException(darling);
        Optional<Address> address = addressRepository.findById(id);
        checkAddressException(address);
        createOrUpdate(address.get(), darlingId, addressDto);
    }

    private void createOrUpdate(Address address, Long darlingId, AddressDto addressDto) {
        address.setCode(addressDto.getCode());
        address.setName(addressDto.getName());

        addressRepository.save(address);

    }

    @Override
    public void delete(Long darlingId, Long id) {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        checkDarlingException(darling);
        addressRepository.deleteById(id);
    }

    public void checkAddressException(Optional<Address> address) {
        if(address.isEmpty()) {
            throw new NotFoundException("Address's id not found");
        }
    }

    public void checkDarlingException(Optional<Darling> darling) {
        if(darling.isEmpty()) {
            throw new NotFoundException("Darling's id not found");
        }
    }
}
