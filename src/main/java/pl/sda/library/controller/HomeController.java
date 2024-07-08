package pl.sda.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.library.repository.BookRepository;
import pl.sda.library.repository.MagazineRepository;

@Controller
public class HomeController {

    private final BookRepository bookRepository;
    private final MagazineRepository magazineRepository;

    public HomeController(BookRepository bookRepository, MagazineRepository magazineRepository) {
        this.bookRepository = bookRepository;
        this.magazineRepository = magazineRepository;
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
        model.addAttribute("books", bookRepository.findTop4ByOrderByIdDesc());
        model.addAttribute("magazines", magazineRepository.findTop4ByOrderByIdDesc());
        return "welcome";
    }
}
