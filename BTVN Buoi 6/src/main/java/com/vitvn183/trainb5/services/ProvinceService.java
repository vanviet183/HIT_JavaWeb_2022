package com.vitvn183.trainb5.services;

import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import com.vitvn183.trainb5.models.dto.ProvinceDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProvinceService {

    public List<Provinces> getAll(Integer page);

    public Optional<Provinces> getById(Long id);

    public List<Districts> getListDistrict(Long id);

    public void addNew(ProvinceDTO provinceDTO);

    public void update(Long id, ProvinceDTO provinceDTO);

    public void delete(Long id);
}
