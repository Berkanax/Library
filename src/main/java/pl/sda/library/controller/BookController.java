package pl.sda.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.sda.library.model.Book;
import pl.sda.library.service.BookService;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void addBook() {
        Book book = new Book("Title", "Author", 2024, 123, "ISBN", "Publisher");
        bookService.saveBook(book);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
