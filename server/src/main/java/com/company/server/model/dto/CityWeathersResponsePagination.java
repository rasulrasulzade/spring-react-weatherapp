package com.company.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CityWeathersResponsePagination {
    private int page;
    private int pageSize;
    private boolean isLast;
    private long totalCount;
    private int totalPages;
}
