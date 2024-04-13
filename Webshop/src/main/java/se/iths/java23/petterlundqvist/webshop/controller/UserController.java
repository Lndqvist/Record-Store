package se.iths.java23.petterlundqvist.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.java23.petterlundqvist.webshop.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String signIn(Model m) {
        userService.signOutUser();
        m.addAttribute("message", "");
        return "sign-in-page";
    }
    @GetMapping("/admin")
    public String adminSignIn(Model m) {
        m.addAttribute("message", "");
        return "admin-sign-in-page";
    }

    @PostMapping("/validate")
    public String validateUser(Model m,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password){
        if(userService.validateUser(email, password)) {
            return "redirect:/home";
        } else {
            m.addAttribute("message","Incorrect email or password");
            return "sign-in-page";
        }

    }
    @GetMapping("/create-account")
    public String createAccount(Model m) {
        m.addAttribute("message", "");
        return "create-account-page";
    }

    @PostMapping("/validate-details")
    public String validateNewAccount(Model m,
                                     @RequestParam("first") String firstName,
                                     @RequestParam("last") String lastName,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password){
        if(userService.createUser(firstName, lastName, email, password)) {
            return "redirect:/home";
        } else {
            m.addAttribute("message","The email is already used, please enter another email");
            return "create-account-page";
        }

    }
    @PostMapping("/validate-admin")
    public String validateAdmin(Model m,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password){
        if(userService.validateAdmin(email, password)) {
            return "redirect:/admin-menu";
        } else {
            m.addAttribute("message","Incorrect email or password");
            return "admin-sign-in-page";
        }

    }


}
