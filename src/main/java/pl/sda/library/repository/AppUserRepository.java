package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.library.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
