package com.vitvn183.trainb5.services;

import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import com.vitvn183.trainb5.models.dto.ProvinceDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {

    public List<Districts> getAll(Integer page);

    public Optional<Districts> getByCode(Long code);

    public void addNew(Long code, DistrictDTO districtDTO);

    public void addList(Long code, List<DistrictDTO> districtDTOList);

    public void update(Long code, DistrictDTO districtDTO);

    public void delete(Long code);
}
