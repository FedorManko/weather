package com.andersen.weather.util;

import static com.andersen.weather.util.TestConstants.CITY;
import static com.andersen.weather.util.TestConstants.DATE;
import static com.andersen.weather.util.TestConstants.LAT;
import static com.andersen.weather.util.TestConstants.LON;
import static com.andersen.weather.util.TestConstants.STATE;
import static com.andersen.weather.util.TestConstants.TEMPERATURES;

import com.andersen.weather.entity.Weather;
import java.time.LocalDate;

public final class EntityCreator {

  public static Weather buildWeather() {
    return Weather.builder()
        .date(LocalDate.parse(DATE))
        .lat(LAT)
        .lon(LON)
        .city(CITY)
        .state(STATE)
        .temperatures(TEMPERATURES)
        .build();
  }
}
