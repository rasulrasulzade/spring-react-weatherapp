package com.company.server.job;

import com.company.server.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherJob {
    private final WeatherService weatherService;

    @Scheduled(cron = "0 0 1 * * *")
    public void loadCityWeathersToDB() {
        weatherService.loadCityWeathersToDB();
    }
}
