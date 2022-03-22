package com.vitvn183.trainb5.controllers;

import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import com.vitvn183.trainb5.models.dto.ProvinceDTO;
import com.vitvn183.trainb5.services.ProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceServiceImp provinceServiceImp;

    // get all
    @GetMapping("")
    public ResponseEntity<?> getALl(@RequestParam(name = "page", required = false) Integer page) {
        List<Provinces> provinces = provinceServiceImp.getAll(page);
        return ResponseEntity.status(200).body(provinces);
    }

    // get by id
    @GetMapping("/{provinceId}")
    public ResponseEntity<?> getById(@PathVariable(name = "provinceId") Long id) {
        Provinces province = provinceServiceImp.getById(id).get();
        return ResponseEntity.status(200).body(province);
    }

    // get List districts detail
    @GetMapping("/{provinceId}/districts-detail")
    public ResponseEntity<?> getDistricts(@PathVariable(name = "provinceId") Long id) {
        List<Districts> districts = provinceServiceImp.getListDistrict(id);
        return ResponseEntity.status(200).body(districts);
    }

    // post add new province
    @PostMapping("")
    public ResponseEntity<?> addNew(@RequestBody ProvinceDTO provinceDTO) {
        provinceServiceImp.addNew(provinceDTO);
        return ResponseEntity.status(201).body("Add success!");
    }

    // patch province
    @PatchMapping("/{provinceId}")
    public ResponseEntity<?> update(@PathVariable(name = "provinceId") Long id, @RequestBody ProvinceDTO provinceDTO) {
        provinceServiceImp.update(id, provinceDTO);
        return ResponseEntity.status(200).body("Update success!");
    }

    // delete province
    @DeleteMapping("/{provinceId}")
    public ResponseEntity<?> delete(@PathVariable(name = "provinceId") Long id) {
        provinceServiceImp.delete(id);
        return ResponseEntity.status(200).body("Delete success!");
    }

}
