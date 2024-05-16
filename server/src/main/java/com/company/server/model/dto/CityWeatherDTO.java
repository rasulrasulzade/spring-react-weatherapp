package com.company.server.model.dto;

public record CityWeatherDTO(Long id, String name, Long temperature, String condition, Long openWeatherId) {
}
