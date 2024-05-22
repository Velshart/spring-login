package pl.umcs.springlogin.data.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umcs.springlogin.data.User;

import java.util.Optional;

public interface UserRepositoryCustom extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User save(User user);
    void deleteById(Long id);
}
