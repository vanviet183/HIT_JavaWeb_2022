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

import javax.transaction.Transactional;
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
            provinces = provinceRepository.findAll(PageRequest.of(page, 10)).getContent();
        }

        return provinces;
    }

    @Override
    public Optional<Provinces> getByCode(Long code) {
        Optional<Provinces> province = provinceRepository.findByCode(code);
        if (province.isEmpty()) {
            throw new NotFoundException("Province code: " + code + " not found!");
        }
        return province;
    }

    @Override
    public List<Districts> getListDistrict(Long code) {
        Optional<Provinces> province = provinceRepository.findByCode(code);
        if(province.isEmpty()) {
            throw new NotFoundException("Province code: " + code + " not found!");
        }

        Provinces provinceDetail = province.get();

        return provinceDetail.getDistricts();
    }

    @Override
    public void addNew(ProvinceDTO provinceDTO) {
        createOrUpdate(new Provinces(), provinceDTO);
    }

    @Override
    @Transactional
    public void addList(List<ProvinceDTO> provinceDTOList) {
        provinceDTOList.forEach(provinceDTO -> {
            createOrUpdate(new Provinces(), provinceDTO);
        });
    }

    @Override
    public void update(Long code, ProvinceDTO provinceDTO) {
        Optional<Provinces> province = provinceRepository.findByCode(code);
        if(province.isEmpty()) {
            throw new NotFoundException("Province code: " + code + " not found!");
        }
        createOrUpdate(province.get(), provinceDTO);
    }

    @Override
    public void delete(Long code) {
        Optional<Provinces> province = provinceRepository.findByCode(code);
        if(province.isEmpty()) {
            throw new NotFoundException("Province code: " + code + " not found!");
        }

        provinceRepository.deleteByCode(code);
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
