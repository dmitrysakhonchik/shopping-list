package by.diem.shoppinglist.services;

import by.diem.shoppinglist.entities.User;
import by.diem.shoppinglist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserRepresentation userRepresentation) {
        User user = new User();
        user.setUsername(userRepresentation.getUsername());
        user.setPassword(passwordEncoder.encode(userRepresentation.getPassword()));
        repository.save(user);
    }
}
