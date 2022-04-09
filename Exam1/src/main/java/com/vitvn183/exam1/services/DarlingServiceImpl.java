package com.vitvn183.exam1.services;

import com.vitvn183.exam1.dao.Address;
import com.vitvn183.exam1.dao.Darling;
import com.vitvn183.exam1.dto.DarlingDto;
import com.vitvn183.exam1.exception.NotFoundException;
import com.vitvn183.exam1.repositories.AddressRepository;
import com.vitvn183.exam1.repositories.DarlingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DarlingServiceImpl implements DarlingService {

    @Autowired
    private DarlingRepository darlingRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Darling> getAll() {
        return darlingRepository.findAll();
    }

    @Override
    public List<Darling> getCurrent() {
        return darlingRepository.findByStatusIsNot(3);
    }

    @Override
    public List<Darling> getDangDoi(Integer status) {
        if (status == 2) {
            return darlingRepository.findByStatus(2);
        }
        return darlingRepository.findByStatus(status);
    }

    @Override
    public Darling getById(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingException(darling);
        return darling.get();
    }

    @Override
    public Darling addNew(DarlingDto darlingDto) throws Exception {
        Darling darlingNew = createOrUpdate(new Darling(), darlingDto);
        return darlingNew;
    }

    @Override
    public Darling update(Long id, DarlingDto darlingDto) throws Exception {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingException(darling);
        Darling darlingUpdate = createOrUpdate(darling.get(), darlingDto);
        return darlingUpdate;
    }

    public Darling createOrUpdate(Darling darling, DarlingDto darlingDto) throws Exception {
        try {
            modelMapper.map(darlingDto, darling);
            return darlingRepository.save(darling);
        } catch (Exception e) {
            throw new Exception("There are exceptions. Can not save darling");
        }

    }

    @Override
    public void delete(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingException(darling);
        darling.get().setStatus(3);
        darlingRepository.save(darling.get());
    }

    @Override
    public List<Address> getAddress(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingException(darling);
        return darling.get().getAddressList();
    }

    @Override
    public List<Darling> getTheoTinh(String provice) {
        List<Darling> darlingList = new ArrayList<>();
        List<Address> addressList = addressRepository.findAll();
        addressList.forEach(item -> {
            if(item.getName().equals(provice)) {
                darlingList.add(item.getDarling());
            }
        });

        return darlingList;
    }

    public void checkDarlingException(Optional<Darling> darling) {
        if(darling.isEmpty()) {
            throw new NotFoundException("Darling's id not found");
        }
    }
}
