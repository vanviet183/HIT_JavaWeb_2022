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

    public Optional<Provinces> getByCode(Long code);

    public List<Districts> getListDistrict(Long code);

    public void addNew(ProvinceDTO provinceDTO);

    public void addList(List<ProvinceDTO> provinceDTOList);

    public void update(Long code, ProvinceDTO provinceDTO);

    public void delete(Long code);
}
