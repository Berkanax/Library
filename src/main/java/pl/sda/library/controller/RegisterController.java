package pl.sda.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.sda.library.model.AppUser;
import pl.sda.library.service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    public void register(String username, String password) {
        AppUser user = new AppUser(username, password);
        userService.saveUser(user);
    }
}
