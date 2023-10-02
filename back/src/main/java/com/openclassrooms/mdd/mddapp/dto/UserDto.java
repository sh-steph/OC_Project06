package com.openclassrooms.mdd.mddapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    @NonNull
    @Size(max = 50)
    private String username;


    @JsonIgnore
    @NonNull
    @Size(max = 100)
    private String password;

    @NonNull
    private Boolean admin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
