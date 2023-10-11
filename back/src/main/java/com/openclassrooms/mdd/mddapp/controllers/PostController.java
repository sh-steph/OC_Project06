package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.dto.ThemeDto;
import com.openclassrooms.mdd.mddapp.dto.UserDto;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        Post post = this.postService.getPostById(Long.valueOf(id));
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
//            get theme
        ThemeDto themeDto = new ThemeDto();
        themeDto.setId(post.getTheme().getId());
        themeDto.setTitle(post.getTheme().getTitle());
        themeDto.setCreatedAt(post.getTheme().getCreatedAt());
        themeDto.setUpdatedAt(post.getTheme().getUpdatedAt());
//        get user
        UserDto userDto = new UserDto();
        userDto.setId(post.getUser().getId());
        userDto.setEmail(post.getUser().getEmail());
        userDto.setUsername(post.getUser().getUsername());
        userDto.setAdmin(post.getUser().getAdmin());
        userDto.setCreatedAt(post.getUser().getCreatedAt());
        userDto.setUpdatedAt(post.getUser().getUpdatedAt());
//        initialize post
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setCreatedAt(LocalDateTime.now());
        postDto.setUpdatedAt(null);
        postDto.setUser(userDto);
        postDto.setTheme(themeDto);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        postService.getAllPosts();
        return ResponseEntity.ok(new PostRequest.PostsResponse(postService.getAllPosts()));
    }

    @PostMapping()
    public ResponseEntity<?> addNewPost(@Valid @RequestBody PostDto postDto) {
        this.postService.addNewPost(postDto);
        return ResponseEntity.ok().body(new PostRequest.MessageResponse("The post was successfully created"));
    }

}
