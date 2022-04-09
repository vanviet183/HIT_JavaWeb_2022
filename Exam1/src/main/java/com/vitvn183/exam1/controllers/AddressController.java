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

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(addressServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(addressServiceImpl.getById(id));
    }

    @GetMapping("/{darlingId}/list")
    public ResponseEntity<?> getByDarlingId(@PathVariable("darlingId") Long darlingId) {
        return ResponseEntity.ok().body(addressServiceImpl.getByDarlingId(darlingId));
    }

    @PostMapping("/{darlingId}")
    public ResponseEntity<?> addNew(@PathVariable("darlingId") Long darlingId, @RequestBody AddressDto addressDto) throws Exception {
        return ResponseEntity.ok().body(addressServiceImpl.addNew(darlingId, addressDto));
    }

    @PatchMapping("/{darlingId}/{id}")
    public ResponseEntity<?> update(@PathVariable("darlingId") Long darlingId, @PathVariable("id") Long id, @RequestBody AddressDto addressDto) throws Exception {
        return ResponseEntity.ok().body(addressServiceImpl.update(darlingId, id, addressDto));
    }

    @DeleteMapping("/{darlingId}")
    public ResponseEntity<?> delete(@PathVariable("darlingId") Long darlingId, @PathVariable("id") Long id) {
        addressServiceImpl.delete(darlingId, id);
        return ResponseEntity.ok().body("Delete than cong");
    }
}
