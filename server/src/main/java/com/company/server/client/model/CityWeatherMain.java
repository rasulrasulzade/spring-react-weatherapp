package com.company.server.client.model;

public record CityWeatherMain(Long temp,
                              Long feels_like,
                              Long temp_min,
                              Long temp_max,
                              Long pressure,
                              Long humidity,
                              Long sea_level,
                              Long grnd_level) {
}
