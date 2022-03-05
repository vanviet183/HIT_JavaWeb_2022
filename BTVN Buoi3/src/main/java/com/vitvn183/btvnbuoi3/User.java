package com.vitvn183.btvnbuoi3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String username;
    private String password;
    private String fullName;

//    public Boolean handleLogin(Object o) {
//        Store store = new Store();
//        List<User> users = store.users;
//        for (User user : users) {
//            if (o.equals(user)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public boolean equals(Object o) {
        Store store = new Store();
        List<User> users = store.users;
        User userLogin = (User) o;
        for(User user : users) {
            if (Objects.equals(user.username, userLogin.username) && Objects.equals(user.password, userLogin.password)) return true;
        }
        return false;
    }


}
