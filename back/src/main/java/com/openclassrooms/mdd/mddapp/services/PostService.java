package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.repositories.PostRepository;
import com.openclassrooms.mdd.mddapp.repositories.ThemeRepository;
import com.openclassrooms.mdd.mddapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ThemeRepository themeRepository;


    public Post getPostById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addNewPost(PostDto postDto, User user) {
        Post post = new Post();
        post.setTheme(themeRepository.getById(postDto.getTheme().getId()));
        post.setUser(user);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setDescription(postDto.getDescription());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        this.postRepository.save(post);
    }
}