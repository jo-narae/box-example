package com.example.box.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

public class CustomPageRequest {

    private final int page;
    private final int size;
    private String sort;

    public CustomPageRequest(Integer page, Integer size, String sort) {
        final int DEFAULT_PAGE = 1;
        final int DEFAULT_SIZE = 10;
        final int MAX_SIZE = 50;

        this.page = (page == null || page <= 0) ? DEFAULT_PAGE : page;
        this.size = (size == null || size <= 0) ? DEFAULT_SIZE : Math.min(size, MAX_SIZE);
        this.sort = sort != null ? sort : "id";
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public PageRequest of() {
        if (!this.sort.equals("id")) return org.springframework.data.domain.PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, sort, "id"));
        else return org.springframework.data.domain.PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, sort));
    }
}
