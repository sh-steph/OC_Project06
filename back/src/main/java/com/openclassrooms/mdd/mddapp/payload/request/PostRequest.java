package com.openclassrooms.mdd.mddapp.payload.request;

import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private Theme theme;
    private User user;
    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageResponse {
        private String message;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostsResponse {
        private Iterable<Post> posts;
    }
}

