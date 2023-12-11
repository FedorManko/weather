package com.andersen.weather.service;

import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;
import java.util.List;

public interface WeatherService {

  /**
   * Creating new weather record in DB.
   *
   * @param request - data for creation weather record
   * @return WeatherResponseDto
   */
  WeatherResponseDto createWeatherRecord(WeatherRequestDto request);

  /**
   * Getting Weather record by id.
   *
   * @param weatherId - Weather id of record id DB.
   * @return WeatherResponseDto
   */
  WeatherResponseDto getWeatherById(Long weatherId);

  /**
   * Getting all Weather records.
   *
   * @param date - date of the record.
   * @param city - city to find record.
   * @param sort - sort by date.
   * @return List of WeatherResponseDto
   */
  List<WeatherResponseDto> getAllWeatherRecords(String date, String city, String sort);

}
