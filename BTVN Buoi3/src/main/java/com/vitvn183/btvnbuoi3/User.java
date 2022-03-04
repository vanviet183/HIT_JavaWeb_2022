package com.vitvn183.btvnbuoi3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String username;
    private String password;
    private String fullName;

    public Boolean handleLogin(String username, String password) {
        Store store = new Store();
        List<User> users = store.users;
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
