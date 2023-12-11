package com.andersen.weather.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.andersen.weather.config.swagger.BadRequestResponse;
import com.andersen.weather.config.swagger.NotFoundResponse;
import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;
import com.andersen.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@Tag(name = "Weather controller", description = "API for work with Weather")
public class WeatherController {

  private final WeatherService weatherService;

  @PostMapping
  @BadRequestResponse
  @ApiResponse(responseCode = "201")
  @Operation(summary = "Creates new weather record")
  public ResponseEntity<WeatherResponseDto> createWeatherRecord(
      @Valid @RequestBody WeatherRequestDto request) {
    var response = weatherService.createWeatherRecord(request);
    return ResponseEntity.status(CREATED).body(response);
  }

  @GetMapping("/{weatherId}")
  @NotFoundResponse
  @ApiResponse(responseCode = "200")
  @Operation(summary = "Returns Weather record by id")
  public ResponseEntity<WeatherResponseDto> getWeatherById(@PathVariable Long weatherId) {
    var response = weatherService.getWeatherById(weatherId);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  @ApiResponse(responseCode = "200")
  @Operation(summary = "Returns Weather record according to parameters")
  public ResponseEntity<List<WeatherResponseDto>> getAllWeatherRecords(
      @RequestParam(required = false) String date,
      @RequestParam(required = false) String city,
      @RequestParam(required = false) String sort) {

    var response = weatherService.getAllWeatherRecords(date, city, sort);
    return ResponseEntity.ok(response);
  }

}
