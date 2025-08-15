package com.nhnacademy.taskapi.category.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_closure")
@IdClass(CategoryClosureId.class)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CategoryClosure {

    @Id
    @Column(name = "ancestor_id", nullable = false)
    private int ancestorId;

    @Id
    @Column(name = "descendant_id", nullable = false)
    private int descendantId;

    @Column(name = "depth", nullable = false)
    private int depth;
}
