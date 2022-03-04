package com.vitvn183.btvnbuoi3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public ResponseEntity<?> getUsers() {
        Store store = new Store();
        List<User> users = store.users;
        return ResponseEntity.ok(users);
    }
}
