package com.travelkit.backend.Repository;

import com.travelkit.backend.domain.Checklist;
import com.travelkit.backend.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ChecklistRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // 체크리스트 저장
    @Transactional
    public Checklist save(Checklist checklist) {
        if (checklist.getId() == null) {
            entityManager.persist(checklist); // 새로운 엔티티 저장
        } else {
            entityManager.merge(checklist); // 기존 엔티티 업데이트
        }
        return checklist;
    }

    // 특정 체크리스트 조회
    public Optional<Checklist> findById(Long id) {
        Checklist checklist = entityManager.find(Checklist.class, id);
        return Optional.ofNullable(checklist); // 값이 없을 경우 Optional.empty() 반환
    }
    
    // 모든 체크리스트 조회
    public List<Checklist> findAll() {
        String query = "SELECT c FROM Checklist c";
        return entityManager.createQuery(query, Checklist.class).getResultList();
    }

    // 모든 체크리스트 조회
    public List<Checklist> findByCity() {
        String query = "SELECT c FROM Checklist c";
        return entityManager.createQuery(query, Checklist.class).getResultList();
    }

    // 체크리스트 삭제
    @Transactional
    public void deleteById(Long id) {
        Checklist checklist = entityManager.find(Checklist.class, id);
        if (checklist != null) {
            entityManager.remove(checklist);
        }
    }

    public List<Checklist> findByDestinationCountry(String country){
        String query = "SELECT c FROM Checklist c WHERE c.destination.country = :country";
        return entityManager.createQuery(query, Checklist.class)
                .setParameter("country", country)
                .getResultList();
    }
}
