package pl.sda.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String newLogin, @RequestParam String newPassword) {
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username(newLogin)
                .password(newPassword)
                .roles("USER")
                .build());
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }
}
