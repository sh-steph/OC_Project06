package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.Theme;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.repositories.PostRepository;
import com.openclassrooms.mdd.mddapp.repositories.ThemeRepository;
import com.openclassrooms.mdd.mddapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ThemeService themeService;


    public Post getPostById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPostsFromTheme(Long themeId) {
        Theme theme = themeService.getThemeById(themeId);
        if (theme == null) {
            throw new NotFoundException("the theme id is missing");
        } else {
            return postRepository.findByTheme_Id(themeId);
        }
    }

    public void addNewPost(Long themeId, PostDto postDto, User user) {
        Post post = new Post();
        post.setTheme(themeService.getThemeById(themeId));
        post.setUser(user);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setDescription(postDto.getDescription());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        this.postRepository.save(post);
    }
}