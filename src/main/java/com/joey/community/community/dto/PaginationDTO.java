package com.joey.community.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO<E> {
    private List<E> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages;
    private int totalPage;
}
