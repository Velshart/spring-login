package pl.umcs.springlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umcs.springlogin.data.Cart;

//@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
