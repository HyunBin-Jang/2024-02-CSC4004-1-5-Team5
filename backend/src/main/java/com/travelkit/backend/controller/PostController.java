package com.travelkit.backend.controller;

import com.travelkit.backend.domain.Post;
import com.travelkit.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시물 생성
    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody Post post) {
        Long postId = postService.createPost(post);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }

    // 모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 특정 게시물 조회
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Optional<Post> updatedPost = Optional.ofNullable(postService.updatePost(id, postDetails.getTitle(), postDetails.getContent()));
        return updatedPost.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // 게시물 좋아요 증가
    @PostMapping("/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long id) {
        Optional<Post> likedPost = postService.likePost(id);
        return likedPost.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
