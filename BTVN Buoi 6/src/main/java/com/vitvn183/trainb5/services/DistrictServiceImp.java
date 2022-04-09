package com.vitvn183.trainb5.services;

import com.github.slugify.Slugify;
import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import com.vitvn183.trainb5.exceptions.NotFoundException;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import com.vitvn183.trainb5.repositories.DistrictRepository;
import com.vitvn183.trainb5.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class DistrictServiceImp implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Districts> getAll(Integer page) {
        List<Districts> districts;
        if (page == null) {
            districts = districtRepository.findAll();
        } else {
            districts = districtRepository.findAll(PageRequest.of(page, 10)).getContent();
        }
        return districts;
    }



    @Override
    public Optional<Districts> getByCode(Long code) {
        Optional<Districts> district = districtRepository.findByCode(code);
        if (district.isEmpty()) {
            throw new NotFoundException("District code: " + code + " not found!");
        }
        return district;
    }

    @Override
    public void addNew(Long code, DistrictDTO districtDTO) {
        Optional<Provinces> province = provinceRepository.findByCode(code);
        if (province.isEmpty()) {
            throw new NotFoundException("Province code: " + code + " not found!");
        }

        Districts districtNew = createOrUpdate(new Districts(), districtDTO);

        districtNew.setParentCode(code);
        districtNew.setProvince(province.get());

        districtRepository.save(districtNew);
    }

    @Override
    @Transactional
    public void addList(Long code, List<DistrictDTO> districtDTOList) {
        Optional<Provinces> province = provinceRepository.findByCode(code);
        if (province.isEmpty()) {
            throw new NotFoundException("Province code: " + code + " not found!");
        }
        districtDTOList.forEach(districtDTO -> {
            Districts districtNew = createOrUpdate(new Districts(), districtDTO);
            districtNew.setParentCode(code);
            districtNew.setProvince(province.get());
            districtRepository.save(districtNew);
        });

    }

    @Override
    public void update(Long code, DistrictDTO districtDTO) {
        Optional<Districts> district = districtRepository.findByCode(code);
        if(district.isEmpty()) {
            throw new NotFoundException("District code: " + code + " not found!");
        }

        Districts districtUpdate = createOrUpdate(district.get(), districtDTO);
        districtRepository.save(districtUpdate);
    }

    @Override
    public void delete(Long code) {
        Optional<Districts> district = districtRepository.findByCode(code);
        if (district.isEmpty()) {
            throw new NotFoundException("District code: " + code + " not found!");
        }
        districtRepository.deleteById(code);
    }

    public Districts createOrUpdate(Districts district, DistrictDTO districtDTO) {
        district.setName(districtDTO.getName());
        district.setType(districtDTO.getType());

        Slugify slg = new Slugify();
        String result = slg.slugify(districtDTO.getName());
        district.setSlug(result);

        if(districtDTO.getType().equals("quan")) {
            district.setNameWithType("Quận " + districtDTO.getName());
        } else if(districtDTO.getType().equals("huyen")) {
            district.setNameWithType("Huyện " + districtDTO.getName());
        } else {
            district.setNameWithType("Huyện " + districtDTO.getName());
        }

        district.setPath(districtDTO.getPath());

        String[] pathWithTypes = districtDTO.getPath().split(",");

        district.setPathWithType(district.getNameWithType() + ", Thành phố " + pathWithTypes[1]);
        district.setCode(districtDTO.getCode());

        return district;
    }
}
