package com.vitvn183.btvnbuoi3.models.mapper;

import com.vitvn183.btvnbuoi3.entity.User;
import com.vitvn183.btvnbuoi3.models.dto.UserDto;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
