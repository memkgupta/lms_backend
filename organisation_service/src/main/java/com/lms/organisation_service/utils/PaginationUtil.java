package com.lms.organisation_service.utils;

import com.lms.organisation_service.dtos.PaginatedResponse;
import org.springframework.data.domain.Page;

public class PaginationUtil {

    public static <T> PaginatedResponse<T> toPaginatedResponse(Page<T> page) {
        PaginatedResponse<T> response = new PaginatedResponse<>();
        response.setTotal((int) page.getTotalElements());
        response.setData(page.getContent());

        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();

        response.setPrevious(currentPage > 0 ? currentPage - 1 : -1);
        response.setNext(currentPage + 1 < totalPages ? currentPage + 1 : -1);

        return response;
    }
}