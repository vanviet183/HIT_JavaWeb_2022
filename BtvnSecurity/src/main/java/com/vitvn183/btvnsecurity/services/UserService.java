package com.vitvn183.btvnsecurity.services;

import com.vitvn183.btvnsecurity.dao.User;
import com.vitvn183.btvnsecurity.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getListUser();

    User createUser(UserDto userDto);

    String deleteUser(Long id);
}
