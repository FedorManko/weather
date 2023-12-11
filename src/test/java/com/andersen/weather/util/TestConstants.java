package com.andersen.weather.util;

import java.util.List;

public final class TestConstants {

  private TestConstants() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static final String DATE = "1985-01-01";
  public static final Long WEATHER_ID = 1L;
  public static final String CITY = "Nashville";
  public static final String STATE = "Tennessee";
  public static final Double LAT = 36.1189;
  public static final Double LON = -86.6892;
  public static final List<Double> TEMPERATURES = List.of(17.3, 16.8, 16.4);
  public static final String SORT = "date";
  public static final String SORT_MINUS = "-date";

}
