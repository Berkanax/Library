package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
