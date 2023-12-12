package com.andersen.weather.integration;

import static com.andersen.weather.util.DtoCreator.buildWeatherRequestDto;
import static com.andersen.weather.util.DtoCreator.buildWeatherResponseDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;
import com.andersen.weather.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("tests")
class WeatherServiceImplIntegrationTest {

  @Autowired
  private WeatherServiceImpl weatherService;

  private WeatherResponseDto weatherResponseDto;

  private WeatherRequestDto weatherRequestDto;

  @BeforeEach
  void setUp() {
    weatherResponseDto = buildWeatherResponseDto();
    weatherRequestDto = buildWeatherRequestDto();
  }

  @Test
  @DirtiesContext
  void createWeatherRecordTest() {
    WeatherResponseDto result = weatherService.createWeatherRecord(weatherRequestDto);
    assertEquals(weatherResponseDto, result);
  }

}