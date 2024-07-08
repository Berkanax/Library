package pl.sda.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.library.model.Book;
import pl.sda.library.repository.BookRepository;
import pl.sda.library.service.BookService;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam String bookTitle, @RequestParam String bookAuthor, @RequestParam String bookPublisher, @RequestParam String bookISBN, @RequestParam int bookYear, @RequestParam int bookPages) {
        Book book = new Book(bookTitle, bookAuthor, bookYear, bookPages, bookPublisher, bookISBN);
        bookRepository.save(book);
        return "redirect:/welcome";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String query, Model model) {
        List<Book> books = bookService.searchBooks(query);
        model.addAttribute("books", books);
        return "search-result";
    }
}
