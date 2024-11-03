package com.travelkit.backend.Repository;

import com.travelkit.backend.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // 아이템 저장
    @Transactional
    public Item save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item); // 새로운 엔티티 저장
        } else {
            entityManager.merge(item); // 기존 엔티티 업데이트
        }
        return item;
    }

    // 특정 아이템 조회
    public Optional<Item> findById(Long id) {
        Item item = entityManager.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    // 모든 아이템 조회
    public List<Item> findAll() {
        String query = "SELECT i FROM Item i";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    // 특정 체크리스트의 모든 아이템 조회
    public List<Item> findAllByChecklistId(Long checklistId) {
        String query = "SELECT i FROM Item i WHERE i.checklist.id = :checklistId";
        return entityManager.createQuery(query, Item.class)
                .setParameter("checklistId", checklistId)
                .getResultList();
    }

    // 아이템 삭제
    @Transactional
    public void deleteById(Long id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            entityManager.remove(item);
        }
    }
}
