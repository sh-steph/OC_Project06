package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.models.Post;
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
    private final UserRepository userRepository;


    public Post getPostById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addNewPost(PostDto postDto) {
        Post post = new Post();
        post.setTheme(themeRepository.getById(postDto.getTheme().getId()));
        post.setUser(userRepository.getById(postDto.getUser().getId()));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setDescription(postDto.getDescription());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        this.postRepository.save(post);
    }
}