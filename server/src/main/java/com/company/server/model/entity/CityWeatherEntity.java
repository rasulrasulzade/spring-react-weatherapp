package com.company.server.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "city_weather")
public class CityWeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private Long temperature;
    private String condition;
    private Long openWeatherId;
}
