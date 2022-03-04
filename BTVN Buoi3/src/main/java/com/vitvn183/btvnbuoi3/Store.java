package com.vitvn183.btvnbuoi3;

import java.util.ArrayList;
import java.util.List;

public class Store {

    public static List<User> users = new ArrayList<User>();
    static {
        users.add(new User(1, "vanviet1", "vanviet1", "Van Viet 1"));
        users.add(new User(2, "vanviet2", "vanviet2", "Van Viet 2"));
        users.add(new User(3, "vanviet3", "vanviet3", "Van Viet 3"));
        users.add(new User(4, "vanviet4", "vanviet4", "Van Viet 4"));
        users.add(new User(5, "vanviet5", "vanviet5", "Van Viet 5"));

    }

}
