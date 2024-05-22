package pl.umcs.springlogin.data.interfaces;

import pl.umcs.springlogin.data.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    String registerUser(User user);
}
