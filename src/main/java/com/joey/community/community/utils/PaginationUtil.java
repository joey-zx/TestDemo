package com.joey.community.community.utils;

import com.joey.community.community.dto.PaginationDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class PaginationUtil {
    private PaginationDTO paginationDTO;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        boolean showPrevious;
        boolean showNext;
        boolean showFirstPage;
        boolean showEndPage;
        List<Integer> pages = new ArrayList<>();
        totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
        page = page < 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        showPrevious = page == 1 ? false : true;
        showNext = page == totalPage ? false : true;
        showFirstPage = pages.contains(1) ? false : true;
        showEndPage = pages.contains(totalPage) ? false : true;
        paginationDTO.setShowFirstPage(showFirstPage);
        paginationDTO.setShowEndPage(showEndPage);
        paginationDTO.setShowPrevious(showPrevious);
        paginationDTO.setShowNext(showNext);
        paginationDTO.setPage(page);
        paginationDTO.setPages(pages);
        paginationDTO.setTotalPage(totalPage);
    }
}
