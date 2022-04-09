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

import java.util.List;
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
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address getById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.get();
    }

    @Override
    public List<Address> getByDarlingId(Long darlingId) {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        checkDarlingException(darling);
        List<Address> addressList = addressRepository.findAddressByDarlingId(darlingId);
        return addressList;
    }

    @Override
    public Address addNew(Long darlingId, AddressDto addressDto) throws Exception {

        return createOrUpdate(new Address(), darlingId, addressDto);
    }

    @Override
    public Address update(Long darlingId, Long id, AddressDto addressDto) throws Exception {
        Optional<Address> address = addressRepository.findById(id);
        checkAddressException(address);

        return createOrUpdate(address.get(), darlingId, addressDto);
    }

    private Address createOrUpdate(Address address, Long darlingId, AddressDto addressDto) throws Exception {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        checkDarlingException(darling);
        try {
            modelMapper.map(addressDto, address);
            address.setDarling(darling.get());
            return addressRepository.save(address);
        } catch (Exception e) {
            throw new Exception("There are exceptions. Can not save darling");
        }
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
