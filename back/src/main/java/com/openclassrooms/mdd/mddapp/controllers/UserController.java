package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.mapper.UserMapper;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            User user = this.userService.findById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            User user = this.userService.findById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(!Objects.equals(userDetails.getUsername(), user.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            this.userService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
