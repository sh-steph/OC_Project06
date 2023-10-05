package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post getPostById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
