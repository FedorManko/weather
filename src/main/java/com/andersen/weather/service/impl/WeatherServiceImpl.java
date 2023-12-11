package com.andersen.weather.service.impl;

import static com.andersen.weather.util.ErrorCodeConstants.WEATHER_RECORD_NOT_FOUND;
import static java.util.Comparator.comparing;

import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;
import com.andersen.weather.entity.Weather;
import com.andersen.weather.mapper.WeatherMapper;
import com.andersen.weather.repository.WeatherRepository;
import com.andersen.weather.service.WeatherService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

  private static final String SORT = "date";

  private final WeatherRepository weatherRepository;

  private final WeatherMapper weatherMapper;

  @Override
  @Transactional
  public WeatherResponseDto createWeatherRecord(WeatherRequestDto request) {
    Weather weather = weatherMapper.toWeather(request);
    return  weatherMapper.toWeatherResponseDto(weatherRepository.save(weather));
  }

  @Override
  public WeatherResponseDto getWeatherById(Long weatherId) {
    return weatherMapper.toWeatherResponseDto(weatherRepository.findById(weatherId)
        .orElseThrow(() -> new EntityNotFoundException(WEATHER_RECORD_NOT_FOUND)));
  }

  @Override
  public List<WeatherResponseDto> getAllWeatherRecords(String date, String city, String sort) {
    List<WeatherResponseDto> weatherResponseDtoList;

    if (date != null) {
      weatherResponseDtoList = getWeatherResponseDtoByDate(date);
      return weatherResponseDtoList;
    }

    if (city != null) {
      weatherResponseDtoList = getWeatherResponseDtoByCity(city);
      return weatherResponseDtoList;
    }

    if (sort != null) {
      weatherResponseDtoList = getWeatherResponseDtoSortByDate(sort);
      return weatherResponseDtoList;
    }

    return getWeatherResponseDto();
  }

  private List<WeatherResponseDto> getWeatherResponseDtoSortByDate(String sort) {
    List<Weather> weatherList = weatherRepository.findAll();

    return SORT.equals(sort)
        ? getWeatherResponseDtoAsc(weatherList)
        : getWeatherResponseDtoDesc(weatherList);
  }

  private List<WeatherResponseDto> getWeatherResponseDtoAsc(List<Weather> weatherList) {
    return weatherList.stream().sorted(comparing(Weather::getDate))
        .map(weatherMapper::toWeatherResponseDto).toList();
  }

  private List<WeatherResponseDto> getWeatherResponseDtoDesc(List<Weather> weatherList) {
    return weatherList.stream().sorted(comparing(Weather::getDate).reversed())
        .map(weatherMapper::toWeatherResponseDto).toList();
  }

  private List<WeatherResponseDto> getWeatherResponseDtoByCity(String city) {
    return weatherRepository.findByCityIgnoreCaseIn(parseCityParameter(city))
        .stream()
        .map(weatherMapper::toWeatherResponseDto)
        .toList();
  }

  private List<WeatherResponseDto> getWeatherResponseDtoByDate(String date) {
    return weatherRepository.findAllByDate(LocalDate.parse(date))
        .stream()
        .map(weatherMapper::toWeatherResponseDto)
        .toList();
  }

  private List<WeatherResponseDto> getWeatherResponseDto() {
    return weatherRepository.findAll()
        .stream()
        .map(weatherMapper::toWeatherResponseDto)
        .toList();
  }

  private List<String> parseCityParameter(String cityParameter) {
    return List.of(cityParameter.split(","));
  }

}
