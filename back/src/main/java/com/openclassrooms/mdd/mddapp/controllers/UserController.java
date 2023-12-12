package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.dto.CommentDto;
import com.openclassrooms.mdd.mddapp.dto.UserDto;
import com.openclassrooms.mdd.mddapp.mapper.UserMapper;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.CommentRequest;
import com.openclassrooms.mdd.mddapp.payload.request.UserRequest;
import com.openclassrooms.mdd.mddapp.payload.response.MessageResponse;
import com.openclassrooms.mdd.mddapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @PutMapping()
    public ResponseEntity<?> updateUsernameAndEmail(@Valid @RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (userDto == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Bad request comment"));
        } else {
            this.userService.updateUsernameAndEmail(user, userDto);
            return ResponseEntity.ok().body(new UserRequest.MessageResponse("The user was successfully updated"));
        }
    }
}
