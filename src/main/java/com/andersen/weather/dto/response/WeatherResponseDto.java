package com.andersen.weather.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record WeatherResponseDto(Long id,
                                 String date,
                                 Double lat,
                                 Double lon,
                                 String city,
                                 String state,
                                 List<Double> temperatures) {
}
