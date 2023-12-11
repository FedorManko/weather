package com.andersen.weather.util;

import static com.andersen.weather.util.TestConstants.CITY;
import static com.andersen.weather.util.TestConstants.DATE;
import static com.andersen.weather.util.TestConstants.LAT;
import static com.andersen.weather.util.TestConstants.LON;
import static com.andersen.weather.util.TestConstants.STATE;
import static com.andersen.weather.util.TestConstants.TEMPERATURES;
import static com.andersen.weather.util.TestConstants.WEATHER_ID;
import static java.lang.String.valueOf;

import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;

public final class DtoCreator {

  public static WeatherResponseDto buildWeatherResponseDto() {
    return WeatherResponseDto.builder()
        .id(WEATHER_ID)
        .date(DATE)
        .lat(LAT)
        .lon(LON)
        .city(CITY)
        .state(STATE)
        .temperatures(TEMPERATURES)
        .build();
  }

  public static WeatherRequestDto buildWeatherRequestDto() {
    return WeatherRequestDto.builder()
        .date(DATE)
        .lat(valueOf(LAT))
        .lon(valueOf(LON))
        .city(CITY)
        .state(STATE)
        .temperatures(TEMPERATURES)
        .build();
  }
}
