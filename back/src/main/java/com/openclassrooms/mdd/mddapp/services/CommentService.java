package com.openclassrooms.mdd.mddapp.services;

import com.openclassrooms.mdd.mddapp.dto.CommentDto;
import com.openclassrooms.mdd.mddapp.dto.PostDto;
import com.openclassrooms.mdd.mddapp.models.Comment;
import com.openclassrooms.mdd.mddapp.models.Post;
import com.openclassrooms.mdd.mddapp.models.User;
import com.openclassrooms.mdd.mddapp.payload.request.PostRequest;
import com.openclassrooms.mdd.mddapp.repositories.CommentRepository;
import com.openclassrooms.mdd.mddapp.repositories.PostRepository;
import com.openclassrooms.mdd.mddapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    public List<Comment> getAllCommentsFromPost(Long postId) {
        Post post = this.postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new NotFoundException("the post id is missing");
        } else {
            return commentRepository.findByPost_Id(postId);
        }
    }

    public void addNewCommentFromPost(Long postId, CommentDto commentDto, User user) {
        Post post = this.postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new NotFoundException("the post id is missing");
        } else {
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            comment.setComment(commentDto.getComment());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUpdatedAt(null);
            this.commentRepository.save(comment);
        }
    }
}
