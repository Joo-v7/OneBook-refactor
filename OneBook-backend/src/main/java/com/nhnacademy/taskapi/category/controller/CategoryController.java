package com.nhnacademy.taskapi.category.controller;


import com.nhnacademy.taskapi.category.domain.Category;
import com.nhnacademy.taskapi.category.dto.CategoryCreateDTO;
import com.nhnacademy.taskapi.category.dto.CategoryResponseDto;
import com.nhnacademy.taskapi.category.dto.CategoryUpdateDTO;
import com.nhnacademy.taskapi.category.service.CategoryClosureService;
import com.nhnacademy.taskapi.category.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task/categories")
@Tag(name = "Category", description = "카테고리를 생성, 수정, 삭제, 조회")  // API 그룹 설명 추가
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryClosureService categoryClosureService;

    @PostMapping
    public ResponseEntity<Category> createCategories(@RequestBody CategoryCreateDTO dto){
        Category category = categoryService.addCategory(dto);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping
    public ResponseEntity<Category> modifyCategories(@RequestBody CategoryUpdateDTO dto){
        Category category = categoryService.updateCategory(dto);
        return ResponseEntity.ok().body(category);
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategories(@PathVariable int categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<Category> getCategories(@PathVariable int categoryId){
        Category category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoryList);
    }

    @GetMapping("/topCategories")
    public List<Category> getTopCategories() {
        return categoryService.getTopLevelCategories(); // 최상위 카테고리와 하위 카테고리를 반환
    }

    @GetMapping("/subCategories/{categoryId}")
    public List<Category> getSubCategories(@PathVariable int categoryId) {
        return categoryService.getSubCategories(categoryId);
    }

    // 기존구조 - 1차 개선
    @GetMapping("/tree")
    public List<CategoryResponseDto> getCategoryTree() {
        return categoryService.getCategoryTree();
    }

    // 기존구조 - 2차 개선
    @GetMapping("/tree2")
    public List<CategoryResponseDto> getCategoryTree2() {
        return categoryService.getCategoryTree2();
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryService.getAllCategoriesByPaging(pageable);
        return ResponseEntity.ok().body(categories);
    }

    /** 생성: parentId 없으면 루트로 생성
     *  POST /task/categories/closure
     */
    @PostMapping("/closure")
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CreateRequest req) {
        return ResponseEntity.ok(categoryClosureService.create(req.getName(), req.getParentId()));
    }

    /** 수정(이동): nodeId를 newParentId 밑으로 이동
     *  PUT /task/categories/closure/move
     */
    @PutMapping("/closure/move")
    public ResponseEntity<Void> move(@RequestBody MoveRequest req) {
        categoryClosureService.move(req.getNodeId(), req.getNewParentId());
        return ResponseEntity.noContent().build();
    }

    /** 삭제: nodeId 서브트리 전체 삭제
     *  DELETE /task/categories/closure/subtree/{nodeId}
     */
    @DeleteMapping("/closure/subtree/{nodeId}")
    public ResponseEntity<Void> deleteSubtree(@PathVariable int nodeId) {
        categoryClosureService.deleteSubtree(nodeId);
        return ResponseEntity.noContent().build();
    }

    /** 조회: rootId 기준 트리(JSON)
     *  GET /task/categories/closure/tree/{rootId}
     */
    @GetMapping("/closure/tree/{rootId}")
    public ResponseEntity<CategoryResponseDto> readTree(@PathVariable int rootId) {
        return ResponseEntity.ok(categoryClosureService.readTree(rootId));
    }

    /** 조회: 헤더 카테고리들
     *
     */
    @GetMapping("/header")
    public List<CategoryResponseDto> getCategoryHeader() {
        return categoryClosureService.getHeaderCategories();
    }

    // ===== 요청 바디 DTO =====
    @Data
    public static class CreateRequest {
        private String name;
        private Integer parentId; // null이면 루트
    }

    @Data
    public static class MoveRequest {
        private int nodeId;
        private int newParentId;
    }

}
