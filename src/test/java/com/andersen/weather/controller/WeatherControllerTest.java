package com.andersen.weather.controller;

import static com.andersen.weather.util.DtoCreator.buildWeatherRequestDto;
import static com.andersen.weather.util.DtoCreator.buildWeatherResponseDto;
import static com.andersen.weather.util.TestConstants.CITY;
import static com.andersen.weather.util.TestConstants.DATE;
import static com.andersen.weather.util.TestConstants.SORT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andersen.weather.dto.request.WeatherRequestDto;
import com.andersen.weather.dto.response.WeatherResponseDto;
import com.andersen.weather.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WeatherController.class)
@AutoConfigureMockMvc(addFilters = false)
class WeatherControllerTest {

  @MockBean
  private WeatherService weatherService;

  @Autowired
  private MockMvc mockMvc;

  private WeatherResponseDto weatherResponseDto;

  private WeatherRequestDto weatherRequestDto;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    weatherResponseDto = buildWeatherResponseDto();
    weatherRequestDto = buildWeatherRequestDto();
    objectMapper = new ObjectMapper();
  }

  @Test
  void createWeatherRecordTest() throws Exception {
    doReturn(weatherResponseDto).when(weatherService)
        .createWeatherRecord(any(WeatherRequestDto.class));

    mockMvc.perform(post("/weather")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(weatherRequestDto)))
        .andExpect(content().json(objectMapper.writeValueAsString(weatherResponseDto)))
        .andExpect(status().isCreated());

    verify(weatherService).createWeatherRecord(any(WeatherRequestDto.class));
  }

  @Test
  void getWeatherByIdTest() throws Exception {
    doReturn(weatherResponseDto).when(weatherService).getWeatherById(anyLong());

    mockMvc.perform(get("/weather/1")
            .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(weatherResponseDto)));

    verify(weatherService).getWeatherById(anyLong());
  }

  @Test
  void getAllWeatherRecordsTest() throws Exception {
    doReturn(List.of(weatherResponseDto)).when(weatherService)
        .getAllWeatherRecords(anyString(), anyString(), anyString());

    mockMvc.perform(get("/weather")
            .param("date", DATE)
            .param("city", CITY)
            .param("sort", SORT)
            .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString((List.of(weatherResponseDto)))));

    verify(weatherService).getAllWeatherRecords(anyString(), anyString(), anyString());
  }

}
