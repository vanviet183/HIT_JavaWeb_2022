package com.vitvn183.btvnsecurity.models;

import com.vitvn183.btvnsecurity.dao.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String jwt;
    private String username;
}
