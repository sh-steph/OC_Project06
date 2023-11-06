package com.openclassrooms.mdd.mddapp.controllers;

import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.dto.ThemeDto;
import com.openclassrooms.mdd.mddapp.dto.UserDto;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.services.PostService;
import com.openclassrooms.mdd.mddapp.services.ThemeService;
import com.openclassrooms.mdd.mddapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final ThemeService themeService;

    @GetMapping("/{id}/theme/{themeId}")
    public ResponseEntity<?> findById(@PathVariable("themeId") Long themeId, @PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = this.postService.getPostById(Long.valueOf(id));
        Theme theme = themeService.getThemeById(themeId);
        User user = userService.findByEmail(authentication.getName());

        if (post == null) {
            return ResponseEntity.notFound().build();
        }
//            get theme
        ThemeDto themeDto = new ThemeDto();
        themeDto.setId(theme.getId());
        themeDto.setTitle(theme.getTitle());
        themeDto.setCreatedAt(theme.getCreatedAt());
        themeDto.setUpdatedAt(theme.getUpdatedAt());
//        get user
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setAdmin(user.getAdmin());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
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

    @GetMapping()
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtos = new ArrayList<>();
        posts.forEach(post -> {
//            get theme
            ThemeDto themeDto = new ThemeDto();
            themeDto.setId(post.getTheme().getId());
            themeDto.setTitle(post.getTheme().getTitle());
            themeDto.setCreatedAt(post.getTheme().getCreatedAt());
            themeDto.setUpdatedAt(post.getTheme().getUpdatedAt());
//            get user
            UserDto userDto = new UserDto();
            userDto.setId(post.getUser().getId());
            userDto.setUsername(post.getUser().getUsername());
            userDto.setEmail(post.getUser().getEmail());
            userDto.setAdmin(post.getUser().getAdmin());
            userDto.setCreatedAt(post.getUser().getCreatedAt());
            userDto.setUpdatedAt(post.getUser().getUpdatedAt());
//            get post
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setUser(userDto);
            postDto.setTitle(post.getTitle());
            postDto.setDescription(post.getDescription());
            postDto.setTheme(themeDto);
            postDto.setCreatedAt(post.getCreatedAt());
            postDto.setUpdatedAt(post.getUpdatedAt());
            postDtos.add(postDto);
        });
        return ResponseEntity.ok(new PostRequest.PostsResponse(postDtos));
    }
//    @GetMapping("/theme/{themeId}")
//    public ResponseEntity<?> getAllPostsFromTheme(@PathVariable("themeId") Long themeId) {
//        postService.getAllPostsFromTheme(themeId);
//        return ResponseEntity.ok(new PostRequest.PostsResponse(postService.getAllPostsFromTheme(themeId)));
//    }

    @PostMapping("/theme/{themeId}")
    public ResponseEntity<?> addNewPost(@PathVariable("themeId") Long themeId, @Valid @RequestBody PostDto postDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        this.postService.addNewPost(themeId, postDto, user);
        return ResponseEntity.ok().body(new PostRequest.MessageResponse("The post was successfully created"));
    }

}
