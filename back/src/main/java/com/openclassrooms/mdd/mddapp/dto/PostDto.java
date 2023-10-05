package com.openclassrooms.mdd.mddapp.dto;


import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
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
public class PostDto {
    private Long id;

    @NonNull
    private Theme theme;

    @NonNull
    private User user;

    @NonNull
    @Size(max = 50)
    @Email
    private String title;

    @NonNull
    @Size(max = 500)
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
