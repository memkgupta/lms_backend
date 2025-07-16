package com.lms.organisation_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    private int total;
    private List<T> data;
    private Integer next;
    private Integer previous;
}