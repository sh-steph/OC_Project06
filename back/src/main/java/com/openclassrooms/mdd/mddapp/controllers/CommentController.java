package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.dto.CommentDto;
import com.openclassrooms.mdd.mddapp.models.Comment;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.CommentRequest;
import com.openclassrooms.mdd.mddapp.payload.response.MessageResponse;
import com.openclassrooms.mdd.mddapp.services.CommentService;
import com.openclassrooms.mdd.mddapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> findAllCommentsFromPost(@PathVariable("postId") Long postId) {

        List<Comment> comment = this.commentService.getAllCommentsFromPost(postId);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommentRequest.MessageResponse("None post"));
        } else {
            return ResponseEntity.ok(new CommentRequest.MessageResponse("get all comments from the post id : " + postId));
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