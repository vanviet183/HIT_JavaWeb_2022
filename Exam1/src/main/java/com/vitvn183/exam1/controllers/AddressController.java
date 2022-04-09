package com.vitvn183.exam1.controllers;

import com.vitvn183.exam1.dto.AddressDto;
import com.vitvn183.exam1.services.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @PostMapping("/{darlingId}")
    public ResponseEntity<?> addNew(@PathVariable("darlingId") Long darlingId, AddressDto addressDto) {
        addressServiceImpl.addNew(darlingId, addressDto);
        return ResponseEntity.ok().body("Add than cong");
    }

    @PatchMapping("/{darlingId}/{id}")
    public ResponseEntity<?> update(@PathVariable("darlingId") Long darlingId, @PathVariable("id") Long id, AddressDto addressDto) {
        addressServiceImpl.update(darlingId, id, addressDto);
        return ResponseEntity.ok().body("Update than cong");
    }

    @DeleteMapping("/{darlingId}")
    public ResponseEntity<?> delete(@PathVariable("darlingId") Long darlingId, @PathVariable("id") Long id, AddressDto addressDto) {
        addressServiceImpl.delete(darlingId, id);
        return ResponseEntity.ok().body("Delete than cong");
    }
}
