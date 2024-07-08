package pl.sda.library.service;

import org.springframework.stereotype.Service;
import pl.sda.library.model.Book;
import pl.sda.library.model.Magazine;
import pl.sda.library.repository.BookRepository;
import pl.sda.library.repository.MagazineRepository;

import java.time.LocalDate;

@Service
public class BorrowService {

    private final BookRepository bookRepository;
    private final MagazineRepository magazineRepository;

    public BorrowService(BookRepository bookRepository, MagazineRepository magazineRepository) {
        this.bookRepository = bookRepository;
        this.magazineRepository = magazineRepository;
    }

    public void borrowBook(Long bookId, String startDate, String readerPesel) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBorrowerPesel(readerPesel);
        book.setBorrowDate(LocalDate.parse(startDate));
        book.setStatus("Wypożyczona");
        bookRepository.save(book);
    }

    public void returnBook(Long bookId, String endDate) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setReturnDate(LocalDate.parse(endDate));
        book.setStatus("Dostępna");
        bookRepository.save(book);
    }

    public void borrowMagazine(Long magazineId, String startDate, String readerPesel) {
        Magazine magazine = magazineRepository.findById(magazineId).orElseThrow(() -> new RuntimeException("Magazine not found"));
        magazine.setBorrowerPesel(readerPesel);
        magazine.setBorrowDate(LocalDate.parse(startDate));
        magazine.setStatus("Wypożyczona");
        magazineRepository.save(magazine);
    }

    public void returnMagazine(Long magazineId, String endDate) {
        Magazine magazine = magazineRepository.findById(magazineId).orElseThrow(() -> new RuntimeException("Magazine not found"));
        magazine.setReturnDate(LocalDate.parse(endDate));
        magazine.setStatus("Dostępna");
        magazineRepository.save(magazine);
    }
}
