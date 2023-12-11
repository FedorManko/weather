package com.andersen.weather.repository;

import com.andersen.weather.entity.Weather;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

  List<Weather> findAllByDate(LocalDate date);

  List<Weather> findByCityIgnoreCaseIn(List<String> strings);

}
