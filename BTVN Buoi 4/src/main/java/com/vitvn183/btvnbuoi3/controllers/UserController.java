package com.vitvn183.btvnbuoi3.controllers;

import com.vitvn183.btvnbuoi3.entity.User;
import com.vitvn183.btvnbuoi3.models.dto.UserDto;
import com.vitvn183.btvnbuoi3.models.mapper.UserMapper;
import com.vitvn183.btvnbuoi3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        List<UserDto> userDtoList = userService.getListUserDtos();
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") long id) {
        User user = userService.getUserByIdApi(id);
        UserDto userDto = UserMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Add user success");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id ,@RequestBody User userUpdate) {
        User user = userService.updateUserApi(id, userUpdate);
        UserDto userDto = UserMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        userService.deleteUserByIdApi(id);
        return ResponseEntity.status(200).body("Delete success");
    }
}
