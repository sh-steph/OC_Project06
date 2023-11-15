package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.dto.CommentDto;
import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.dto.ThemeDto;
import com.openclassrooms.mdd.mddapp.dto.UserDto;
import com.openclassrooms.mdd.mddapp.models.Comment;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.CommentRequest;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.payload.response.MessageResponse;
import com.openclassrooms.mdd.mddapp.services.CommentService;
import com.openclassrooms.mdd.mddapp.services.PostService;
import com.openclassrooms.mdd.mddapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> findAllCommentsFromPost(@PathVariable("postId") Long postId) {

        List<Comment> comments = commentService.getAllCommentsFromPost(postId);
        List<CommentDto> commentDtos = new ArrayList<>();
        if (comments == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommentRequest.MessageResponse("None post"));
        } else {
            comments.forEach(comment -> {
                CommentDto commentDto = new CommentDto();
//                           get user
                UserDto userDto = new UserDto();
                userDto.setId(comment.getUser().getId());
                userDto.setUsername(comment.getUser().getUsername());
                userDto.setEmail(comment.getUser().getEmail());
                userDto.setAdmin(comment.getUser().getAdmin());
                userDto.setCreatedAt(comment.getUser().getCreatedAt());
                userDto.setUpdatedAt(comment.getUser().getUpdatedAt());
                ThemeDto themeDto = new ThemeDto();
                themeDto.setId(comment.getPost().getTheme().getId());
                themeDto.setTitle(comment.getPost().getTheme().getTitle());
                themeDto.setDescription(comment.getPost().getTheme().getDescription());
                themeDto.setCreatedAt(comment.getPost().getTheme().getCreatedAt());
                themeDto.setUpdatedAt(comment.getPost().getTheme().getUpdatedAt());
//                            get post
                PostDto postDto = new PostDto();
                postDto.setId(comment.getPost().getId());
                postDto.setUser(userDto);
                postDto.setTitle(comment.getPost().getTitle());
                postDto.setDescription(comment.getPost().getDescription());
                postDto.setTheme(themeDto);
                postDto.setCreatedAt(comment.getPost().getCreatedAt());
                postDto.setUpdatedAt(comment.getPost().getUpdatedAt());
                commentDto.setId(comment.getId());
                commentDto.setUser(userDto);
                commentDto.setPost(postDto);
                commentDto.setComment(comment.getComment());
                commentDto.setCreatedAt(comment.getCreatedAt());
                commentDto.setUpdatedAt(comment.getUpdatedAt());
                commentDtos.add(commentDto);
            });
            return ResponseEntity.ok(new CommentRequest.CommentResponse(commentDtos));
        }
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<?> addNewCommentFromPost(@PathVariable("postId") Long postId, @Valid @RequestBody CommentDto commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        if (commentDto == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Bad request comment"));
        } else {
            this.commentService.addNewCommentFromPost(postId, commentDto, user);
            return ResponseEntity.ok().body(new CommentRequest.MessageResponse("The comment was successfully created"));
        }
    }
}