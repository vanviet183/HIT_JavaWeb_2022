package com.vitvn183.btvnbuoi3.controllers;

import com.vitvn183.btvnbuoi3.entity.User;
import com.vitvn183.btvnbuoi3.models.dto.UserDto;
import com.vitvn183.btvnbuoi3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    private static boolean error = false;
    private static boolean login = false;

    // get page login
    @RequestMapping (value={"/", "/login"}, method = RequestMethod.GET)
    public String getLogin(Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/success")
    public String getResult(@ModelAttribute User user) {
        List<User> users = userService.getListUsers();
        if (users.contains(user)) {
            error = false;
            login = true;
            return "redirect:/users";
        } else {
            error = true;
            return "redirect:/login";
        }
    }

    // get List user
    @GetMapping("/users")
    public String getListUser(Model model) {
        List<UserDto> userDtoList = userService.getListUserDtos();
        model.addAttribute("userDTOs", userDtoList);
//        if(login) {
//            return "success";
//        }
//        login = false;
//        return "redirect:/login";
        return "success";
    }

    // get user by id
    @GetMapping("/user/findById")
    @ResponseBody
    public Optional<User> getUser(long id) {
//        User user = userService.getUserById(id).get();
//        UserDto userDto = UserMapper.toUserDto(user);
        return userService.getUserById(id);
    }

    // addNew user
    @PostMapping("/users/addNew")
    public String addNew(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    // update user
    @RequestMapping(value="/user/update", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String update(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    // delete user
    @RequestMapping(value="/user/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
