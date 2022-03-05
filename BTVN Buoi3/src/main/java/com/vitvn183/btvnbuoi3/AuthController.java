package com.vitvn183.btvnbuoi3;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private static boolean error = false;
    private static boolean login = false;

    // get page login
    @RequestMapping (value={"/", "/login"}, method = RequestMethod.GET)
    public String getLogin(Model model) {
        model.addAttribute("error", error);
        return "login";
    }

//    // handle login
//    @GetMapping ("/success")
//    public String getResult(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
//        User user = new User();
//        if (user.handleLogin(username, password)) {
//            error = false;
//            return "redirect:/users";
//        } else {
//            error = true;
//            return "redirect:/login";
//        }
//    }

    @GetMapping("/success")
    public String getResult(@ModelAttribute User user) {
        User userLogin = new User();
        if (userLogin.equals(user)) {
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
        Store store = new Store();
        model.addAttribute("users", store.users);
        if(login) {
            return "success";
        }
        login = false;
        return "redirect:/login";
    }

}
