package com.company.server.repository;

import com.company.server.model.entity.CityWeatherEntity;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeatherEntity, Long> {

    @Query("select case when count(*) > 0 then true else false end from CityWeatherEntity")
    Boolean existsAny();
}
