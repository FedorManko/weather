package com.andersen.weather.dto.request;

import static com.andersen.weather.util.ConstraintConstants.COMMON_MAX_SIZE;
import static com.andersen.weather.util.ErrorCodeConstants.CITY_NAME_IS_TOO_LONG;
import static com.andersen.weather.util.ErrorCodeConstants.LAT_IS_INCORRECT;
import static com.andersen.weather.util.ErrorCodeConstants.LON_IS_INCORRECT;
import static com.andersen.weather.util.ErrorCodeConstants.STATE_NAME_IS_TOO_LONG;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;

@Builder
public record WeatherRequestDto(

    String date,

    @NotBlank(message = LAT_IS_INCORRECT)
    String lat,

    @NotBlank(message = LON_IS_INCORRECT)
    String lon,
    @Size(max = COMMON_MAX_SIZE, message = CITY_NAME_IS_TOO_LONG)
    String city,

    @Size(max = COMMON_MAX_SIZE, message = STATE_NAME_IS_TOO_LONG)
    String state,
    List<Double> temperatures) {

}
