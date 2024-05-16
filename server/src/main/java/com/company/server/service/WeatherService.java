package com.company.server.service;

import com.company.server.model.dto.CityWeathersResponse;

public interface WeatherService {
    CityWeathersResponse getCityWeathers(int pageNumber, int pageSize, String sort);

    void loadCityWeathersToDB();
}
