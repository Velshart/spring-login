package pl.umcs.springlogin.data.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umcs.springlogin.data.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
