package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.LoginRequest;
import com.openclassrooms.mdd.mddapp.payload.request.SignupRequest;
import com.openclassrooms.mdd.mddapp.payload.response.JwtResponse;
import com.openclassrooms.mdd.mddapp.payload.response.MessageResponse;
import com.openclassrooms.mdd.mddapp.repositories.UserRepository;
import com.openclassrooms.mdd.mddapp.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        Optional<User> userOptional = userRepository.findByEmail(signUpRequest.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                false);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwt = jwtService.generateToken(user);

        boolean isAdmin = false;
        if (user != null) {
            isAdmin = user.getAdmin();
        }

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                isAdmin));
    }
}
