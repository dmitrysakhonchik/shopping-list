package by.diem.shoppinglist.configs;

import by.diem.shoppinglist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("USER"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
    }
}
