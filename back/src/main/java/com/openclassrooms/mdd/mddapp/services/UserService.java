package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.dto.UserDto;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        String regexPattern = "^(.+)@(\\S+)$";
        if (Pattern.compile(regexPattern).matcher(value).matches()) {
            User userFind = userRepository.findByEmail(value)
                    .orElseThrow(() -> new UsernameNotFoundException(value));
        return new User(
                userFind.getId(),
                userFind.getEmail(),
                userFind.getUsername(),
                userFind.getPassword(),
                userFind.getAdmin(),
                userFind.getCreatedAt(),
                userFind.getUpdatedAt());
        } else {
            User userFind = userRepository.findByUsername(value)
                    .orElseThrow(() -> new UsernameNotFoundException(value));
            return new User(
                    userFind.getId(),
                    userFind.getEmail(),
                    userFind.getUsername(),
                    userFind.getPassword(),
                    userFind.getAdmin(),
                    userFind.getCreatedAt(),
                    userFind.getUpdatedAt());
        }
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email)  {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public User findByEmailOrUsername(String email, String username)  {
        Optional<User> optionalUser = userRepository.findByEmailOrUsername(email, username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public User findByUsername(String username)  {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void updateUsernameAndEmail(User currentUser, UserDto newUsernameAndEmail) {
        User user = new User();
        user.setId(currentUser.getId());
        user.setUsername(newUsernameAndEmail.getUsername());
        user.setEmail(newUsernameAndEmail.getEmail());
        user.setAdmin(currentUser.getAdmin());
        user.setPassword(currentUser.getPassword());
        user.setCreatedAt(currentUser.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        this.userRepository.save(user);
    }
}
