package com.vitvn183.exam1.services;

import com.vitvn183.exam1.dao.Address;
import com.vitvn183.exam1.dao.Darling;
import com.vitvn183.exam1.dto.DarlingDto;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Service
public interface DarlingService {
    public List<Darling> getAll();

    public List<Darling> getCurrent();

    public List<Darling> getDangDoi(Integer status);

    public Darling getById(Long id);

    public Darling addNew(DarlingDto darlingDto) throws Exception;

    public Darling update(Long id, DarlingDto darlingDto) throws Exception;

    public void delete(Long id);

    public List<Address> getAddress(Long id);

    public List<Darling> getTheoTinh(String provice);

}
