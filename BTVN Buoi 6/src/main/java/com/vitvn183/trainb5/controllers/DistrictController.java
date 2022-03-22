package com.vitvn183.trainb5.controllers;

import com.vitvn183.trainb5.entity.Districts;
import com.vitvn183.trainb5.entity.Provinces;
import com.vitvn183.trainb5.models.dto.DistrictDTO;
import com.vitvn183.trainb5.services.DistrictServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/districts")
public class DistrictController {

    @Autowired
    private DistrictServiceImp districtServiceImp;

    // get list districts
    @GetMapping("")
    public ResponseEntity<?> getALl(@RequestParam(name = "page", required = false) Integer page) {
        List<Districts> districts = districtServiceImp.getAll(page);
        return ResponseEntity.status(200).body(districts);
    }

    // get district by id
    @GetMapping("/{districtId}")
    public ResponseEntity<?> getDistrict(@PathVariable(name = "districtId") Long id) {
        Districts district = districtServiceImp.getById(id).get();
        return ResponseEntity.status(200).body(district);
    }

    // post add new district
    @PostMapping("/{provinceId}")
    public ResponseEntity<?> addNew(@PathVariable(name = "provinceId") Long provinceId, @RequestBody DistrictDTO districtDTO) {
        districtServiceImp.addNew(provinceId, districtDTO);
        return ResponseEntity.status(201).body("Add success!");
    }

    // patch district
    @PatchMapping("/{districtId}")
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
