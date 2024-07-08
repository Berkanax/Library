package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.library.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
