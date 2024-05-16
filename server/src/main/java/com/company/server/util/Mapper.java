package com.company.server.util;

import com.company.server.client.model.CityWeather;
import com.company.server.model.dto.CityWeatherDTO;
import com.company.server.model.entity.CityWeatherEntity;

import java.util.List;

public class Mapper {
    public static List<CityWeatherDTO> mapToCityWeatherDTOList(List<CityWeather> cityWeathers) {
        return cityWeathers.stream()
                .map(cityWeather ->
                        new CityWeatherDTO(cityWeather.id(), cityWeather.name(), cityWeather.main().temp(),
                                cityWeather.weather().get(0).main(),
                                cityWeather.id()
                        ))
                .toList();
    }

    public static List<CityWeatherEntity> mapToCityWeatherEntityList(List<CityWeather> cityWeathers) {
        return cityWeathers.stream()
                .map(cityWeather -> {
                    CityWeatherEntity ent = new CityWeatherEntity();
                    ent.setName(cityWeather.name());
                    ent.setTemperature(cityWeather.main().temp());
                    ent.setCondition(cityWeather.weather().get(0).main());
                    ent.setOpenWeatherId(cityWeather.id());
                    return ent;
                })
                .toList();
    }

    public static List<CityWeatherDTO> mapToCityWeatherDTO(List<CityWeatherEntity> entities) {
        return entities.stream()
                .map(ent -> new CityWeatherDTO(ent.getId(), ent.getName(), ent.getTemperature(),
                        ent.getCondition(),
                        ent.getOpenWeatherId()))
                .toList();
    }
}
