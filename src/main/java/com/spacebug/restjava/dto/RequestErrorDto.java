package com.spacebug.restjava.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Schema(
    name = "Error",
    description = "Represents an error response from an API request."
)
public class RequestErrorDto {


  @Schema(
      accessMode = AccessMode.READ_ONLY,
      description = "The HTTP response code.",
      example = "404"
  )
  private Integer code;

  @Schema(
      accessMode = AccessMode.READ_ONLY,
      description = "The HTTP response code message.",
      example = "Not Found"
  )
  private String message;

  @Schema(
      accessMode = AccessMode.READ_ONLY,
      description = "The details of the error message.",
      example = "User with supplied identifier not found."
  )
  private String details;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
