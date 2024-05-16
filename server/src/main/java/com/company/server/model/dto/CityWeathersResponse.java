package com.company.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CityWeathersResponse {
    private List<CityWeatherDTO> cities;
    private CityWeathersResponsePagination pagination;
}
