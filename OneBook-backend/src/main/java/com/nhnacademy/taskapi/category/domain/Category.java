package com.nhnacademy.taskapi.category.domain;

import com.nhnacademy.taskapi.book.domain.BookCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_parent_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<Category> childrenCategory = new ArrayList<>();

    @NotBlank
    @Length(max = 20)
    @Column(nullable = false)
    private String name;

    private boolean status = false;

    // 4개짜리 생성자 추가
    public Category(int categoryId, Category parentCategory, String name, boolean status) {
        this.categoryId = categoryId;
        this.parentCategory = parentCategory;
        this.childrenCategory = Collections.emptyList();
        this.name = name;
        this.status = status;
    }
}


