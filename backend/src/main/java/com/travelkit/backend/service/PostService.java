package com.travelkit.backend.service;

import com.travelkit.backend.Repository.PostRepository;
import com.travelkit.backend.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 작성
    public Long createPost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    // 모든 게시글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 게시글 조회
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // 게시글 수정
    public Post updatePost(Long id, String title, String content) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setTitle(title);
            post.setContent(content);
            return postRepository.update(post);
        }
        return null;
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}
