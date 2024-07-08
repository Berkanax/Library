package pl.sda.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.library.model.Book;
import pl.sda.library.service.BookService;

import java.util.List;

@RestController
public class SearchController {

    private final BookService bookService;

    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
}
