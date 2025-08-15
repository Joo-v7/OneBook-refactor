package com.nhnacademy.taskapi.category.service;

import com.nhnacademy.taskapi.category.domain.Category;
import com.nhnacademy.taskapi.category.dto.CategoryResponseDto;
import com.nhnacademy.taskapi.category.repository.CategoryClosureRepository;
import com.nhnacademy.taskapi.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryClosureService {
    private final CategoryRepository categoryRepository;       // 기본 엔티티용
    private final CategoryClosureRepository closureRepository; // 클로저 전용(조회/경로 갱신)

    // 새 카테고리 등록: parentId 있으면 그 밑에 붙임
    public CategoryResponseDto create(String name, Integer parentId) {
        Category c = new Category();
        c.setName(name);
        if (parentId != null) {
            // 프록시만 들고옴(여기선 쿼리 안 나감)
            c.setParentCategory(categoryRepository.getReferenceById(parentId));
        }
        categoryRepository.save(c); // INSERT 후 ID 확보

        // 클로저 경로 세팅
        closureRepository.insertSelf(c.getCategoryId()); // (자기자신,자기자신,0)
        if (parentId != null) {
            // 부모 체인 전체를 새 노드 조상으로 연결
            closureRepository.linkToParentChain(c.getCategoryId(), parentId);
        }
        return toDto(c);
    }

    // 노드 이동: nodeId를 newParentId 밑으로
    public void move(int nodeId, int newParentId) {
        // 부모 포인터만 먼저 교체
        Category n = categoryRepository.getReferenceById(nodeId);
        n.setParentCategory(categoryRepository.getReferenceById(newParentId));

        // 클로저 경로는 싹 갈아끼움: 기존 경로 지우고 -> 새 부모 체인 기준으로 다시 생성
        closureRepository.unlinkOldAncestors(nodeId);
        closureRepository.linkToNewParentChain(nodeId, newParentId);

        // TODO: 사이클 방지 체크(자기 자손 밑으로 못 가게)
        // if (closureRepository.findSubtree(nodeId).stream().anyMatch(c -> c.getCategoryId() == newParentId)) throw ...
    }

    // 서브트리 통째 삭제
    public void deleteSubtree(int nodeId) {
        // 클로저로 서브트리 한 방 조회(루트 포함)
        List<Category> subtree = closureRepository.findSubtree(nodeId);
        // 자식부터 지우자(부모 FK 위반 방지)
        for (int i = subtree.size() - 1; i >= 0; i--) {
            categoryRepository.delete(subtree.get(i));
        }
        // FK ON DELETE CASCADE면 클로저 테이블은 자동 정리됨
    }

    // 루트 기준 트리 조회 -> 트리 DTO로 반환
    @Transactional(readOnly = true)
    public CategoryResponseDto readTree(int rootId) {
        // 클로저 덕분에 서브트리 SELECT 1번
        List<Category> flat = closureRepository.findSubtree(rootId);

        // parentId -> children 맵
        Map<Integer, List<Category>> byParent = flat.stream()
                .filter(x -> x.getParentCategory() != null) // 루트 제외
                .collect(Collectors.groupingBy(x -> x.getParentCategory().getCategoryId()));

        // 루트 찾고
        Category root = flat.stream()
                .filter(x -> x.getCategoryId() == rootId)
                .findFirst()
                .orElseThrow();

        // 재귀로 붙여서 트리 DTO 만든다
        return toDtoTree(root, byParent);
    }

    // --- DTO 매핑 ---

    private CategoryResponseDto toDto(Category c) {
        return new CategoryResponseDto(
                c.getCategoryId(),
                c.getName(),
                c.isStatus(),
                List.of()
        );
    }

    private CategoryResponseDto toDtoTree(Category c, Map<Integer, List<Category>> byParent) {
        List<Category> children = byParent.getOrDefault(c.getCategoryId(), List.of());
        return new CategoryResponseDto(
                c.getCategoryId(),
                c.getName(),
                c.isStatus(),
                children.stream()
                        .map(ch -> toDtoTree(ch, byParent))
                        .collect(Collectors.toList())
        );
    }

    @Cacheable(cacheNames = "headerCategories", key = "'v1'", sync = true)
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getHeaderCategories() {
        List<Object[]> rows = closureRepository.findHeaderCategories();

        // rootId -> rootDto
        Map<Integer, CategoryResponseDto> roots = new LinkedHashMap<>();

        for (Object[] r : rows) {
            Integer rootId     = (Integer) r[0];
            String  rootName   = (String)  r[1];
            Boolean rootStatus = (Boolean) r[2];   // status가 tinyint면 Boolean으로 매핑됨

            Integer childId    = (Integer) r[3];  // 자식 없으면 null
            String  childName  = (String)  r[4];
            Boolean childStatus= (Boolean) r[5];

            // 루트 DTO 없으면 만들기
            CategoryResponseDto rootDto = roots.get(rootId);
            if (rootDto == null) {
                rootDto = new CategoryResponseDto(rootId, rootName, rootStatus != null && rootStatus, new ArrayList<>());
                roots.put(rootId, rootDto);
            }

            // 자식 있으면 붙이기 (직계만)
            if (childId != null) {
                CategoryResponseDto childDto =
                        new CategoryResponseDto(childId, childName, childStatus != null && childStatus, new ArrayList<>());
                rootDto.getChildren().add(childDto); // 필드명이 children/subCategories 중 뭐든 맞춰서 사용
            }
        }

        return new ArrayList<>(roots.values());
    }
}
