package com.example.infrastructureForProjects.Controller;

import com.example.infrastructureForProjects.Model.User;
import com.example.infrastructureForProjects.Security.WebSecurityConfig;
import com.example.infrastructureForProjects.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** controls login page urls */
@Controller
public class LoginController {

    @Autowired
    private WebSecurityConfig webSecurityConfig;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
    public String homePage(Model model){
        if(webSecurityConfig.isUserLoggedIn())
            model.addAttribute("currentuser", webSecurityConfig.getUsername());
        return "index";
    }

    @GetMapping(value = "/register")
    public String registerPage(){
        return "register";
    }

    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/aboutus")
    public String aboutUsPage(){
        return "aboutus";
    }

    @PostMapping(value = "/register")
    public String addUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "confirmation") String confirmPassword, RedirectAttributes redirectAttributes) {
        if(userService.findUserByUsername(username).isPresent())
        {
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists. Try again.");
            return "redirect:/register";
        }
        else if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Passwords do not match. Try again.");
            return "redirect:/register";
        }
        else {
            User user = new User(username, password);
            userService.save(user);
            return "redirect:/";
        }
    }

    @GetMapping("/login-error")
    public String loginUser(Model model) {
        model.addAttribute("errorMessage", "The username or password is incorrect. Try again.");
        return "login.html";
    }
}
