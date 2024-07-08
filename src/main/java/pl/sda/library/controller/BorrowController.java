package pl.sda.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.library.service.BorrowService;

@Controller
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/borrowBook")
    public String borrowBook(@RequestParam Long bookId, @RequestParam String startDate, @RequestParam String readerPesel, Model model) {
        borrowService.borrowBook(bookId, startDate, readerPesel);
        return "redirect:/book-template?id=" + bookId;
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam Long bookId, @RequestParam String endDate, Model model) {
        borrowService.returnBook(bookId, endDate);
        return "redirect:/book-template?id=" + bookId;
    }

    @PostMapping("/borrowMagazine")
    public String borrowMagazine(@RequestParam Long magazineId, @RequestParam String startDate, @RequestParam String readerPesel, Model model) {
        borrowService.borrowMagazine(magazineId, startDate, readerPesel);
        return "redirect:/magazine-template?id=" + magazineId;
    }

    @PostMapping("/returnMagazine")
    public String returnMagazine(@RequestParam Long magazineId, @RequestParam String endDate, Model model) {
        borrowService.returnMagazine(magazineId, endDate);
        return "redirect:/magazine-template?id=" + magazineId;
    }
}
