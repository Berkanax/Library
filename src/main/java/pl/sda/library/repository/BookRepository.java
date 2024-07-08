package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.library.model.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findTop4ByOrderByIdDesc();

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}
