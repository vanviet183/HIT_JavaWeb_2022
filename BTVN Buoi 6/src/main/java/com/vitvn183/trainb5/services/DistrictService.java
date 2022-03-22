package com.vitvn183.trainb5.services;

import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {

    public List<Districts> getAll(Integer page);

    public Optional<Districts> getById(Long id);

    public void addNew(Long id, DistrictDTO districtDTO);

    public void update(Long id, DistrictDTO districtDTO);

    public void delete(Long id);
}
