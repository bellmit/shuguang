package com.sofn.common.exception;

import feign.Response;

public class FeignException extends RuntimeException {
  private final String methodKey;
  private Response response;


  FeignException(String methodKey, Response response) {
    this.methodKey = methodKey;
    this.response = response;
  }


  public Response getResponse() {
    return response;
  }

  public String getMethodKey() {
    return methodKey;
  }
}
