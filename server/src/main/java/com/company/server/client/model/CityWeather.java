package com.company.server.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CityWeather(Long id, String name, List<WeatherItem> weather, CityWeatherMain main) {
}
