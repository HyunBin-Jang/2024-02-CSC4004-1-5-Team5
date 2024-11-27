package com.travelkit.backend.Repository;

import com.travelkit.backend.domain.Checklist;
import com.travelkit.backend.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Post post) {
        em.persist(post); // 새로운 엔티티 저장
    }

    // 모든 게시글 조회
    public List<Post> findAll() {
        String query = "SELECT p FROM Post p";
        return em.createQuery(query, Post.class).getResultList();
    }

    // 특정 게시글 조회
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post); // 엔티티가 없을 경우 Optional.empty() 반환
    }

    // 게시글 업데이트
    @Transactional
    public Post update(Post post) {
        return em.merge(post); // 수정된 엔티티 저장
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long id) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            em.remove(post);
        }
    }
    // 좋아요 수 상위 5개
    public List<Post> findTop5ByLikes() {
        String query = "SELECT p FROM Post p ORDER BY p.likes DESC LIMIT 5";
        return em.createQuery(query, Post.class).getResultList();
    }

    // 특정 나라 게시물
    public List<Post> findByCountry(String country) {
        String query = "SELECT p FROM Post p WHERE p.country = :country";
        return em.createQuery(query, Post.class).getResultList();
    }
}