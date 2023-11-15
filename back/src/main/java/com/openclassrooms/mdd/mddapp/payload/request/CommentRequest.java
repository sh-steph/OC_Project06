package com.openclassrooms.mdd.mddapp.payload.request;

import com.openclassrooms.mdd.mddapp.dto.CommentDto;
import com.openclassrooms.mdd.mddapp.models.Comment;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private User user;
    private Post post;
    private String comment;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageResponse {
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponse {
        private Iterable<CommentDto> comments;
    }
}
