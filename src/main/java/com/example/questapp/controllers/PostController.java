package com.example.questapp.controllers;

import com.example.questapp.entities.Post;
import com.example.questapp.requests.PostCreateRequest;
import com.example.questapp.requests.PostUpdateRequest;
import com.example.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postSerivce;

    public PostController(PostService postService) {
        this.postSerivce = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return postSerivce.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postSerivce.getPost(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPost) {
        return postSerivce.createPost(newPost);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId,  @RequestBody PostUpdateRequest updatedPost) {
        return postSerivce.updatePost(postId, updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postSerivce.deletePost(postId);
    }


}
