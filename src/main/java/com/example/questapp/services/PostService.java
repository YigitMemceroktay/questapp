package com.example.questapp.services;

import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.PostRepository;
import com.example.questapp.requests.PostCreateRequest;
import com.example.questapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;
    PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getPost(Long userId) {
        return postRepository.findById(userId).orElse(null);
    }

    public Post createPost(PostCreateRequest newPost) {
        User user = userService.findByUserId(newPost.getUserId());
        if(user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPost.getId());
        toSave.setText(newPost.getText());
        toSave.setTitle(newPost.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updatePost(Long postId, PostUpdateRequest updatedPost) {
    Optional<Post> post = postRepository.findById(postId);
    if(post.isPresent()) {
        Post toUpdate = post.get();
        toUpdate.setText(updatedPost.getText());
        toUpdate.setTitle(updatedPost.getTitle());
        postRepository.save(toUpdate);
        return toUpdate;
    }
    return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
