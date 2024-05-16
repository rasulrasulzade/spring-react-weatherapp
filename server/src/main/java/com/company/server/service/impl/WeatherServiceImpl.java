package com.company.server.service.impl;

import com.company.server.client.OpenWeatherMapClient;
import com.company.server.client.model.CityWeather;
import com.company.server.model.dto.CityWeathersResponse;
import com.company.server.model.dto.CityWeathersResponsePagination;
import com.company.server.model.entity.CityWeatherEntity;
import com.company.server.repository.CityWeatherRepository;
import com.company.server.service.WeatherService;
import com.company.server.util.Mapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.company.server.constants.CityConstants.IDS;
import static com.company.server.util.Mapper.mapToCityWeatherEntityList;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    private final CityWeatherRepository cityWeatherRepository;
    private final OpenWeatherMapClient openWeatherMapClient;

    @Override
    public CityWeathersResponse getCityWeathers(int pageNumber, int pageSize, String sort) {
        String[] sortParts = sort.split(",");
        String sortBy = sortParts[0];
        Sort.Direction direction = Sort.Direction.valueOf(sortParts[1].toUpperCase());
        int page = pageNumber < 1 ? 0 : pageNumber - 1;

        Page<CityWeatherEntity> pageData = cityWeatherRepository.findAll(PageRequest.of(page,
                pageSize,
                direction,
                sortBy));

        return CityWeathersResponse.builder()
                .cities(Mapper.mapToCityWeatherDTO(pageData.getContent()))
                .pagination(CityWeathersResponsePagination.builder()
                        .page(pageData.getNumber() + 1)
                        .pageSize(pageData.getSize())
                        .isLast(pageData.isLast())
                        .totalCount(pageData.getTotalElements())
                        .totalPages(pageData.getTotalPages())
                        .build())
                .build();
    }

    @PostConstruct
    @Override
    public void loadCityWeathersToDB() {
        if (cityWeatherRepository.existsAny()) {
            cityWeatherRepository.deleteAll();
        }

        List<CityWeather> cityWeathers = getCityWeathersIds();
        List<CityWeatherEntity> entities = mapToCityWeatherEntityList(cityWeathers);
        cityWeatherRepository.saveAll(entities);
        log.info("New weather data saved to the DB");
    }

    public List<CityWeather> getCityWeathersIds() {
        List<CityWeather> cityWeathers = new ArrayList<>();

        final int availableProcessorCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(availableProcessorCount);
        List<Callable<CityWeather>> tasks = new ArrayList<>();
        for (long id : IDS) {
            tasks.add(() -> openWeatherMapClient.getCityWeatherByID(id, "metric"));
        }

        try {
            List<Future<CityWeather>> futures = executorService.invokeAll(tasks);
            for (Future<CityWeather> future : futures) {
                cityWeathers.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
        return cityWeathers;
    }
}
