package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.library.model.Magazine;
import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    List<Magazine> findTop4ByOrderByIdDesc();
}
