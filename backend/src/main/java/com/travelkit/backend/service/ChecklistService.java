package com.travelkit.backend.service;

import com.travelkit.backend.Repository.ChecklistRepository;
import com.travelkit.backend.Repository.ItemRepository;
import com.travelkit.backend.domain.Checklist;
import com.travelkit.backend.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ChecklistService(ChecklistRepository checklistRepository, ItemRepository itemRepository) {
        this.checklistRepository = checklistRepository;
        this.itemRepository = itemRepository;
    }

    // 체크리스트 생성
    public Checklist createChecklist(Checklist checklist) {
        return checklistRepository.save(checklist);
    }

    // 특정 체크리스트 조회
    public Optional<Checklist> getChecklistById(Long id) {
        return checklistRepository.findById(id);
    }

    // 모든 체크리스트 조회
    public List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    // 체크리스트 삭제
    public void deleteChecklist(Long id) {
        checklistRepository.deleteById(id);
    }

    // 체크리스트에 아이템 추가
    public Item addItemToChecklist(Long checklistId, Item item) {
        Checklist checklist = checklistRepository.findById(checklistId).orElseThrow(() -> new RuntimeException("Checklist not found"));
        item.setChecklist(checklist);
        return itemRepository.save(item);
    }

    // 체크리스트의 모든 아이템 조회
    public List<Item> getItemsByChecklistId(Long checklistId) {
        return itemRepository.findAllByChecklistId(checklistId);
    }
}
