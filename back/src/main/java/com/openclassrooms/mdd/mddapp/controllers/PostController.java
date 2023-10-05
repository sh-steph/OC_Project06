package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Post post = this.postService.getPostById(Long.valueOf(id));

            if (post == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.postService.getPostById(id));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        postService.getAllPosts();
        return ResponseEntity.ok(new PostRequest.PostsResponse(postService.getAllPosts()));
    }
}
