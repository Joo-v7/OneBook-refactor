package com.nhnacademy.taskapi.category.domain;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class CategoryClosureId implements Serializable {
    private int ancestorId;
    private int descendantId;
}

