package com.vitvn183.trainb5.controllers;

import com.vitvn183.trainb5.base.UrlConstant;
import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import com.vitvn183.trainb5.services.DistrictServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/districts")
public class DistrictController {

    @Autowired
    private DistrictServiceImp districtServiceImp;

    // get list districts
    @GetMapping(UrlConstant.Districts.GET_DISTRICTS)
    public ResponseEntity<?> getALl(@RequestParam(name = "page", required = false) Integer page) {
        List<Districts> districts = districtServiceImp.getAll(page);
        return ResponseEntity.status(200).body(districts);
    }

    // get district by id
    @GetMapping(UrlConstant.Districts.DISTRICT_WITH_CODE)
    public ResponseEntity<?> getDistrict(@PathVariable(name = "code") Long code) {
        Districts district = districtServiceImp.getByCode(code).get();
        return ResponseEntity.status(200).body(district);
    }

    // post add new district
    @PostMapping(UrlConstant.Districts.DISTRICT_WITH_CODE)
    public ResponseEntity<?> addNew(@PathVariable(name = "code") Long code, @RequestBody DistrictDTO districtDTO) {
        districtServiceImp.addNew(code, districtDTO);
        return ResponseEntity.status(201).body("Add success!");
    }

    @PostMapping(UrlConstant.Districts.DISTRICT_WITH_CODE)
    public ResponseEntity<?> addList(@PathVariable(name = "code") Long code, @RequestBody List<DistrictDTO> districtDTOList) {
        districtServiceImp.addList(code, districtDTOList);
        return ResponseEntity.status(201).body("Add success!");
    }

    // patch district
    @PatchMapping(UrlConstant.Districts.COLLECTION)
    public ResponseEntity<?> update(@PathVariable(name = "districtId") Long id, @RequestBody DistrictDTO districtDTO) {
        districtServiceImp.update(id, districtDTO);
        return ResponseEntity.status(200).body("Update success!");
    }

    // delete district
    @DeleteMapping("/{districtId}")
    public ResponseEntity<?> delete(@PathVariable(name = "districtId") Long id) {
        districtServiceImp.delete(id);
        return ResponseEntity.status(200).body("Delete success!");
    }
}
