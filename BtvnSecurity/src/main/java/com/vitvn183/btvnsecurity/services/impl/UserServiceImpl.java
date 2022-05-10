package com.vitvn183.btvnsecurity.services.impl;

import com.vitvn183.btvnsecurity.dao.User;
import com.vitvn183.btvnsecurity.dto.UserDto;
import com.vitvn183.btvnsecurity.exceptions.NotFoundException;
import com.vitvn183.btvnsecurity.repositories.UserRepository;
import com.vitvn183.btvnsecurity.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        checkUserException(id);
        userRepository.deleteById(id);
        return "Delete successfully";
    }

    public void checkUserException(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Can not find user by id");
        }
    }
}
