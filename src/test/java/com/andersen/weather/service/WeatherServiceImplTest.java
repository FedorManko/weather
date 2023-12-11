package com.andersen.weather.service;

import static com.andersen.weather.util.DtoCreator.buildWeatherRequestDto;
import static com.andersen.weather.util.DtoCreator.buildWeatherResponseDto;
import static com.andersen.weather.util.EntityCreator.buildWeather;
import static com.andersen.weather.util.ErrorCodeConstants.WEATHER_RECORD_NOT_FOUND;
import static com.andersen.weather.util.TestConstants.CITY;
import static com.andersen.weather.util.TestConstants.DATE;
import static com.andersen.weather.util.TestConstants.SORT;
import static com.andersen.weather.util.TestConstants.SORT_MINUS;
import static com.andersen.weather.util.TestConstants.WEATHER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.andersen.weather.WeatherServiceImpl;
import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;
import com.andersen.weather.entity.Weather;
import com.andersen.weather.mapper.WeatherMapper;
import com.andersen.weather.repository.WeatherRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

  @InjectMocks
  private WeatherServiceImpl weatherService;

  @Mock
  private WeatherRepository weatherRepository;
  @Mock
  private WeatherMapper weatherMapper;

  private Weather weather;

  private WeatherResponseDto weatherResponseDto;

  private WeatherRequestDto weatherRequestDto;

  @BeforeEach
  void setUp() {
    weather = buildWeather();
    weatherResponseDto = buildWeatherResponseDto();
    weatherRequestDto = buildWeatherRequestDto();
  }

  @Test
  void createWeatherRecord() {
    doReturn(weather).when(weatherMapper).toWeather(any(WeatherRequestDto.class));
    doReturn(weather).when(weatherRepository).save(any(Weather.class));
    doReturn(weatherResponseDto).when(weatherMapper).toWeatherResponseDto(any(Weather.class));

    weatherService.createWeatherRecord(weatherRequestDto);

    verify(weatherMapper).toWeather(any(WeatherRequestDto.class));
    verify(weatherRepository).save(any(Weather.class));
    verify(weatherMapper).toWeatherResponseDto(any(Weather.class));
  }

  @Test
  void getWeatherById() {
    doReturn(Optional.of(weather)).when(weatherRepository).findById(anyLong());
    doReturn(weatherResponseDto).when(weatherMapper).toWeatherResponseDto(weather);

    weatherService.getWeatherById(WEATHER_ID);

    verify(weatherRepository).findById(anyLong());
    verify(weatherMapper).toWeatherResponseDto(any(Weather.class));
  }

  @Test
  void getWeatherByIdThrowEntityNotFoundException() {
    doReturn(Optional.empty()).when(weatherRepository).findById(anyLong());

    Executable result = () -> weatherService.getWeatherById(WEATHER_ID);

    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, result);
    assertThat(exception.getMessage()).isEqualTo(WEATHER_RECORD_NOT_FOUND);
    verify(weatherRepository).findById(anyLong());
  }

  @Test
  void getAllWeatherRecordsAllParamNull() {
    doReturn(List.of(weather)).when(weatherRepository).findAll();
    doReturn(weatherResponseDto).when(weatherMapper).toWeatherResponseDto(weather);

    weatherService.getAllWeatherRecords(null, null, null);

    verify(weatherRepository).findAll();
    verify(weatherMapper).toWeatherResponseDto(any(Weather.class));
  }

  @Test
  void getAllWeatherRecordsWhenDateIsPresent() {
    doReturn(List.of(weather)).when(weatherRepository).findAllByDate(any(LocalDate.class));
    doReturn(weatherResponseDto).when(weatherMapper).toWeatherResponseDto(weather);

    weatherService.getAllWeatherRecords(DATE, null, null);

    verify(weatherRepository).findAllByDate(any(LocalDate.class));
    verify(weatherMapper).toWeatherResponseDto(any(Weather.class));
  }

  @Test
  void getAllWeatherRecordsWhenCityIsPresent() {
    doReturn(List.of(weather)).when(weatherRepository).findByCityIgnoreCaseIn(anyList());
    doReturn(weatherResponseDto).when(weatherMapper).toWeatherResponseDto(weather);

    weatherService.getAllWeatherRecords(null, CITY, null);

    verify(weatherRepository).findByCityIgnoreCaseIn(anyList());
    verify(weatherMapper).toWeatherResponseDto(any(Weather.class));
  }

  @Test
  void getAllWeatherRecordsWhenSortIsPresent() {
    doReturn(List.of(weather)).when(weatherRepository).findAll();
    doReturn(weatherResponseDto).when(weatherMapper).toWeatherResponseDto(weather);

    weatherService.getAllWeatherRecords(null, null, SORT);

    verify(weatherRepository).findAll();
    verify(weatherMapper).toWeatherResponseDto(any(Weather.class));
  }

  @Test
  void getAllWeatherRecordsWhenSortMinusIsPresent() {
    doReturn(List.of(weather)).when(weatherRepository).findAll();
    doReturn(weatherResponseDto).when(weatherMapper).toWeatherResponseDto(weather);

    weatherService.getAllWeatherRecords(null, null, SORT_MINUS);

    verify(weatherRepository).findAll();
    verify(weatherMapper).toWeatherResponseDto(any(Weather.class));
  }

}