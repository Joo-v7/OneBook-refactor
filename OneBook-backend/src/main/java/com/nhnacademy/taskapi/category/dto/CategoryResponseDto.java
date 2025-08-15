package com.nhnacademy.taskapi.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private int id;
    private String name;
    private boolean status;
    private List<CategoryResponseDto> children;

}
