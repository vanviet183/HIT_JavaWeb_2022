package com.vitvn183.exam1.services;

import com.vitvn183.exam1.dto.AddressDto;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    public void addNew(Long darlingId, AddressDto addressDto);

    public void update(Long darlingId, Long id, AddressDto addressDto);

    public void delete(Long darlingId, Long id);
}
