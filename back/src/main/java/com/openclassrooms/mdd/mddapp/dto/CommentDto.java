package com.openclassrooms.mdd.mddapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;

    private UserDto user;

    private PostDto post;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

