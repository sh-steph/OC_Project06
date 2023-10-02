package com.openclassrooms.mdd.mddapp.payload.request;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;
}
