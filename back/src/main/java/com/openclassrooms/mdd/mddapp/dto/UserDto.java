package com.openclassrooms.mdd.mddapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private String username;

    private Boolean admin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

