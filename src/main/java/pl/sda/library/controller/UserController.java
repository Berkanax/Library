package pl.sda.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.library.model.AppUser;
import pl.sda.library.repository.AppUserRepository;

@Controller
public class UserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(String newLogin, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return "redirect:/index.html?error=PasswordsDoNotMatch";
        }
        AppUser user = new AppUser();
        user.setUsername(newLogin);
        user.setPassword(passwordEncoder.encode(newPassword));
        appUserRepository.save(user);
        return "redirect:/index.html?success=UserRegistered";
    }
}
