package com.vitvn183.btvnbuoi3.services;

import com.vitvn183.btvnbuoi3.entity.User;
import com.vitvn183.btvnbuoi3.exceptions.ErrorResponse;
import com.vitvn183.btvnbuoi3.exceptions.NotFoundException;
import com.vitvn183.btvnbuoi3.models.dto.UserDto;
import com.vitvn183.btvnbuoi3.models.mapper.UserMapper;
import com.vitvn183.btvnbuoi3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // get list User
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    // get list UserDto
    public List<UserDto> getListUserDtos() {
        List<User> users = getListUsers();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : users) {
            userDtoList.add(UserMapper.toUserDto(user));
        }
        return userDtoList;
    }

    // get User by Id
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    // get User by id Api
    public User getUserByIdApi(long id) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundException("Id invalid");
    }

    // save or update user
    public void save(User user) {
        userRepository.save(user);
    }

    // delete user
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    // update user Api
    public User updateUserApi(long id, User userUpdate) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getId() == id) {
                user.setFullName(userUpdate.getFullName());
                user.setUsername(userUpdate.getUsername());
                user.setPassword(userUpdate.getPassword());
                return user;
            }
        }

        throw new NotFoundException("Id invalid");
    }

    // delete User by id Api
    public void deleteUserByIdApi(long id) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getId() == id) {
                userRepository.deleteById(id);
            }
        }
        throw new NotFoundException("Id invalid");
    }
}
