package com.z100.cocktailshop.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ApiException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleApiRequestException(ApiException e) {

		ApiError error = new ApiError(
				e.getMessage(),
				HttpStatus.BAD_REQUEST,
				null,
				ZonedDateTime.now(ZoneId.of("Z"))
		);

		return buildResponseEntity(error);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleGeneralException(Exception e) {

		ApiError error = new ApiError(
				e.getMessage(),
				HttpStatus.BAD_REQUEST,
				null,
				ZonedDateTime.now(ZoneId.of("Z"))
		);

		return buildResponseEntity(error);
	}


	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {

		return new ResponseEntity<>(apiError, apiError.status());
	}
}
