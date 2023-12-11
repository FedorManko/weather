package com.andersen.weather.mapper;

import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;
import com.andersen.weather.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WeatherMapper {

  WeatherResponseDto toWeatherResponseDto(Weather weather);

  Weather toWeather(WeatherRequestDto request);

}
