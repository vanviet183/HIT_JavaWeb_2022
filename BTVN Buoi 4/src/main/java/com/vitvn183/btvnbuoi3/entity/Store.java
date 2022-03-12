package com.vitvn183.btvnbuoi3.entity;

import java.util.ArrayList;
import java.util.List;

public class Store {

    public static List<User> users = new ArrayList<User>();
    static {
        users.add(new User(1L, "vanviet1", "vanviet1", "Van Viet 1"));
        users.add(new User(2L, "vanviet2", "vanviet2", "Van Viet 2"));
        users.add(new User(3L, "vanviet3", "vanviet3", "Van Viet 3"));
        users.add(new User(4L, "vanviet4", "vanviet4", "Van Viet 4"));
        users.add(new User(5L, "vanviet5", "vanviet5", "Van Viet 5"));

    }

}
