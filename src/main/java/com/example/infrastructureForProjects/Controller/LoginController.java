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

   /* @PreAuthorize("isAuthenticated()")
    public String currentUserName() {
        return userService.findByUsername();
    } */

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
    public String addUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "confirmation") String confirmPassword, /*Errors errors,*/ RedirectAttributes redirectAttributes) {
        //if(userService.findByUsername(username) != null)
        if(userService.findUserByUsername(username).isPresent())
        {
            //errors.rejectValue("usernameExists", "Username already exists. Try again.");
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists. Try again.");
            return "redirect:/register";
        }
        else if (!password.equals(confirmPassword)) {
            //errors.rejectValue("passwordFailMatch", "Passwords do not match. Try again.");
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

  /*  @PostMapping(value = "/doLogin")
    public String doLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        //if(userService.findByUsername(username) != null)
        if(userService.findUserByUsername(username).isPresent())
        {
            //System.out.print("HEREEEE:" + userService.findUserByUsername(username).isPresent());
            return "/login-error";      //would need to return a failed login
        }
        else if (userService.findPassword(password)) {      //note to self: optimize later
            return "login-error";       //would need to return a failed login
        }
        else {
            return "/";                 //would need to return a successful login
        }
    } */


    /*@GetMapping("/users")
    public List<User> allUsers() {
        return userService.listAll();
    }

    @GetMapping(path="/users/count")
    public Long count() {

        return userService.count();
    }*/

   /* @DeleteMapping(path="/users/{id}")
    public void delete(@PathVariable String id) {

        Long userId = Long.parseLong(id);
        userService.deleteById(userId);
    } */
}
