package com.andersen.weather.dto.response;

import com.andersen.weather.enums.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

  private ErrorCode code;

  private String message;

  private Instant timestamp;

}
