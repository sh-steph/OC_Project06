package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFind = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(
                userFind.getId(),
                userFind.getEmail(),
                userFind.getEmail(),
                userFind.getPassword(),
                userFind.getAdmin(),
                userFind.getCreatedAt(),
                userFind.getUpdatedAt());
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
    public User findByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
