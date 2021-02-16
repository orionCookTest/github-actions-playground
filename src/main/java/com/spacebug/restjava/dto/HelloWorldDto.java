package com.spacebug.restjava.dto;

public class HelloWorldDto {
  private String message;

  public HelloWorldDto(String message) {this.message = message;}

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
