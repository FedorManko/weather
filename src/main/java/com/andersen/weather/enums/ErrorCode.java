package com.andersen.weather.enums;

import com.andersen.weather.util.ErrorCodeConstants;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  CITY_NAME_IS_TOO_LONG(ErrorCodeConstants.CITY_NAME_IS_TOO_LONG),
  STATE_NAME_IS_TOO_LONG(ErrorCodeConstants.STATE_NAME_IS_TOO_LONG),
  LAT_IS_INCORRECT(ErrorCodeConstants.LAT_IS_INCORRECT),
  LON_IS_INCORRECT(ErrorCodeConstants.LON_IS_INCORRECT),
  WEATHER_RECORD_NOT_FOUND(ErrorCodeConstants.WEATHER_RECORD_NOT_FOUND),
  UNEXPECTED_ERROR_OCCURRED(ErrorCodeConstants.UNEXPECTED_ERROR_OCCURRED);

  private final String message;

  public static ErrorCode valueOfMessage(String message) {
    return Stream.of(values())
        .filter(errorCode -> errorCode.getMessage().equals(message))
        .findFirst()
        .orElse(UNEXPECTED_ERROR_OCCURRED);
  }

}
