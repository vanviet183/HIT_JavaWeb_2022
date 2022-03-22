package com.vitvn183.trainb5.services;

import com.github.slugify.Slugify;
import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import com.vitvn183.trainb5.exceptions.NotFoundException;
import com.vitvn183.trainb5.models.dto.ProvinceDTO;
import com.vitvn183.trainb5.repositories.DistrictRepository;
import com.vitvn183.trainb5.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProvinceServiceImp implements ProvinceService {

    @Autowired
    public DistrictRepository districtRepository;

    @Autowired
    public ProvinceRepository provinceRepository;

    @Override
    public List<Provinces> getAll(Integer page) {
        List<Provinces> provinces;
        if(page == null) {
            provinces = provinceRepository.findAll();
        } else {
            provinces = provinceRepository.findAll(PageRequest.of(page, 3)).getContent();
        }

        return provinces;
    }

    @Override
    public Optional<Provinces> getById(Long id) {
        Optional<Provinces> province = provinceRepository.findById(id);
        if (province.isEmpty()) {
            throw new NotFoundException("Province id: " + id + " not found!");
        }
        return province;
    }

    @Override
    public List<Districts> getListDistrict(Long id) {
        Optional<Provinces> province = provinceRepository.findById(id);
        if(province.isEmpty()) {
            throw new NotFoundException("Province id: " + id + " not found!");
        }

        Provinces provinceDetail = province.get();

        return provinceDetail.getDistricts();
    }

    @Override
    public void addNew(ProvinceDTO provinceDTO) {
        createOrUpdate(new Provinces(), provinceDTO);
    }

    @Override
    public void update(Long id, ProvinceDTO provinceDTO) {
        Optional<Provinces> province = provinceRepository.findById(id);
        if(province.isEmpty()) {
            throw new NotFoundException("Province id: " + id + " not found!");
        }
        createOrUpdate(province.get(), provinceDTO);
    }

    @Override
    public void delete(Long id) {
        Optional<Provinces> province = provinceRepository.findById(id);
        if(province.isEmpty()) {
            throw new NotFoundException("Province id: " + id + " not found!");
        }

        provinceRepository.deleteById(id);
    }

    public void createOrUpdate(Provinces province, ProvinceDTO provinceDTO) {
        province.setName(provinceDTO.getName());

        Slugify slg = new Slugify();
        String result = slg.slugify(provinceDTO.getName());
        province.setSlug(result);

        province.setType(provinceDTO.getType());

        if(provinceDTO.getType().equals("tinh")) {
            province.setNameWithType("Tỉnh " + provinceDTO.getName());
        } else if(provinceDTO.getType().equals("thanh-pho")) {
            province.setNameWithType("Thành phố " + provinceDTO.getName());
        } else {
            province.setNameWithType("Thành phố " + provinceDTO.getName());
        }

        province.setCode(provinceDTO.getCode());

        provinceRepository.save(province);
    }
}
