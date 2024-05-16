package com.company.server.controller;

import com.company.server.model.dto.CityWeathersResponse;
import com.company.server.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/cities/weathers")
    public CityWeathersResponse getCityWeathers(@RequestParam("page") int page,
                                                @RequestParam("pageSize") int pageSize,
                                                @RequestParam("sort") String sort) {
        return weatherService.getCityWeathers(page, pageSize, sort);
    }
}
