package pl.sda.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.library.service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(String newLogin, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return "redirect:/index.html?error=PasswordsDoNotMatch";
        }
        userService.registerUser(newLogin, newPassword);
        return "redirect:/index.html?success=UserRegistered";
    }
}
