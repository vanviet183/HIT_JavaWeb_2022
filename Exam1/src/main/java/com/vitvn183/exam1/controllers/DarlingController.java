package com.vitvn183.exam1.controllers;


import com.vitvn183.exam1.dao.Address;
import com.vitvn183.exam1.dao.Darling;
import com.vitvn183.exam1.dto.DarlingDto;
import com.vitvn183.exam1.services.DarlingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/darlings")
public class DarlingController {

    @Autowired
    private DarlingServiceImpl darlingServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(darlingServiceImpl.getAll());
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent() {
        return ResponseEntity.ok().body(darlingServiceImpl.getCurrent());
    }

    // them /status cho do trung uri @@
    @GetMapping("/status")
    public ResponseEntity<?> getDangDoi(@RequestParam(value = "status", required = false) Integer status) {
        return ResponseEntity.ok().body(darlingServiceImpl.getDangDoi(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(darlingServiceImpl.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addNew(@RequestBody DarlingDto darlingDto) throws Exception {
        return ResponseEntity.ok().body(darlingServiceImpl.addNew(darlingDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, DarlingDto darlingDto) throws Exception {
        return ResponseEntity.ok().body(darlingServiceImpl.update(id, darlingDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        darlingServiceImpl.delete(id);
        return ResponseEntity.ok().body("Delete thanh cong");
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<?> getAddresses(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(darlingServiceImpl.getAddress(id));
    }

    @GetMapping("/q")
    public ResponseEntity<?> getByProvince(@RequestParam(value = "q", required = false) String province) {
        return ResponseEntity.ok().body(darlingServiceImpl.getTheoTinh(province));
    }
}
