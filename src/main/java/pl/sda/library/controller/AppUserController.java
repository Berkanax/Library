package pl.sda.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.library.model.AppUser;
import pl.sda.library.service.AppUserService;

@Controller
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/user/register")
    public String register(@RequestParam String newLogin, @RequestParam String newPassword, @RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {
        if (!newPassword.equals(confirmPassword)) {
            return "redirect:/index.html?error=PasswordsDoNotMatch";
        }
        AppUser user = appUserService.registerUser(newLogin, newPassword);
        redirectAttributes.addFlashAttribute("currentUser", user);
        return "redirect:/welcome.html"; // Poprawne przekierowanie
    }

    @PostMapping("/user/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        AppUser user = appUserService.loginUser(username, password);
        if (user != null) {
            redirectAttributes.addFlashAttribute("currentUser", user);
            return "redirect:/welcome.html"; // Poprawne przekierowanie
        }
        return "redirect:/index.html?error=InvalidCredentials";
    }
}
