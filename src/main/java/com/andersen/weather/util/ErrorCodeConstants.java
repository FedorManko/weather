package com.andersen.weather.util;

import static com.andersen.weather.util.ConstraintConstants.COMMON_MAX_SIZE;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCodeConstants {

  public static final String LENGTH_FIX_ADVICE =
      " Please try to use no more than " + COMMON_MAX_SIZE + " characters";
  public static final String CITY_NAME_IS_TOO_LONG = "City name is too long" + LENGTH_FIX_ADVICE;
  public static final String LAT_IS_INCORRECT = "Lat is incorrect";
  public static final String LON_IS_INCORRECT = "Lon is incorrect";
  public static final String STATE_NAME_IS_TOO_LONG = "State name is too long" + LENGTH_FIX_ADVICE;
  public static final String UNEXPECTED_ERROR_OCCURRED = "Unexpected error has occurred";

  public static final String WEATHER_RECORD_NOT_FOUND = "Weather record not found";
}
