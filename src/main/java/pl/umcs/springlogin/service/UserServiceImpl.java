package pl.umcs.springlogin.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.umcs.springlogin.data.Cart;
import pl.umcs.springlogin.data.Role;
import pl.umcs.springlogin.data.User;
import pl.umcs.springlogin.repository.RoleRepository;
import pl.umcs.springlogin.repository.UserRepositoryCustom;
import pl.umcs.springlogin.data.interfaces.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepositoryCustom.findByUsername(username);
    }

    @Transactional
    public String registerUser(User user) {
        if(userRepositoryCustom.findByUsername(user.getUsername()).isPresent()) {
            return "failure";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setCart(new Cart());

        Role userRole = roleRepository.findByName("USER").orElseGet(null);
        if(userRole != null) {
            user.getRoles().add(userRole);
        }else {
            Role role = new Role();
            role.setName("USER");
            user.getRoles().add(role);
        }

        userRepositoryCustom.save(user);
        return "success";
    }

    @Transactional
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = ((UserDetails) principal).getUsername();

        return userRepositoryCustom.findByUsername(username).orElse(null);
    }
}
