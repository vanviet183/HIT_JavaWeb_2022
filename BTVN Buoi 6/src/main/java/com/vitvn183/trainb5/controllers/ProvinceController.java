package com.vitvn183.trainb5.controllers;

import com.vitvn183.trainb5.base.RestApiV1;
import com.vitvn183.trainb5.base.UrlConstant;
import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import com.vitvn183.trainb5.models.dto.ProvinceDTO;
import com.vitvn183.trainb5.services.ProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class ProvinceController {

    @Autowired
    private ProvinceServiceImp provinceServiceImp;

    // get all
    @GetMapping(UrlConstant.Provinces.GET_PROVINCES)
    public ResponseEntity<?> getALl(@RequestParam(name = "page", required = false) Integer page) {
        List<Provinces> provinces = provinceServiceImp.getAll(page);
        return ResponseEntity.status(200).body(provinces);
    }

    // get by id
    @GetMapping(UrlConstant.Provinces.PROVINCE_WITH_CODE)
    public ResponseEntity<?> getByCode(@PathVariable(name = "code") Long code) {
        Provinces province = provinceServiceImp.getByCode(code).get();
        return ResponseEntity.status(200).body(province);
    }

    // get List districts detail
    @GetMapping(UrlConstant.Provinces.DISTRICT_WITH_CODE_PROVINCE)
    public ResponseEntity<?> getDistricts(@PathVariable(name = "code") Long code) {
        List<Districts> districts = provinceServiceImp.getListDistrict(code);
        return ResponseEntity.status(200).body(districts);
    }

    // post add new province
    @PostMapping(UrlConstant.Provinces.PREFIX)
    public ResponseEntity<?> addNew(@RequestBody ProvinceDTO provinceDTO) {
        provinceServiceImp.addNew(provinceDTO);
        return ResponseEntity.status(201).body("Add success!");
    }

    @PostMapping(UrlConstant.Provinces.COLLECTION)
    public ResponseEntity<?> addList(@RequestBody List<ProvinceDTO> provinceDTOList) {
        provinceServiceImp.addList(provinceDTOList);
        return ResponseEntity.status(201).body("Add success!");
    }

    // patch province
    @PatchMapping(UrlConstant.Provinces.PROVINCE_WITH_CODE)
    public ResponseEntity<?> update(@PathVariable(name = "code") Long code, @RequestBody ProvinceDTO provinceDTO) {
        provinceServiceImp.update(code, provinceDTO);
        return ResponseEntity.status(200).body("Update success!");
    }

    // delete province
    @DeleteMapping(UrlConstant.Provinces.PROVINCE_WITH_CODE)
    public ResponseEntity<?> delete(@PathVariable(name = "code") Long code) {
        provinceServiceImp.delete(code);
        return ResponseEntity.status(200).body("Delete success!");
    }

}
