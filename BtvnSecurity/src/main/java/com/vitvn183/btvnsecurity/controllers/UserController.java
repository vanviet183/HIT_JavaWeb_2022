package com.vitvn183.btvnsecurity.controllers;

import com.vitvn183.btvnsecurity.base.RestApiUsers;
import com.vitvn183.btvnsecurity.dto.UserDto;
import com.vitvn183.btvnsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiUsers
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getListUser());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.createUser(userDto));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }
}
