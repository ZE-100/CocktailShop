package com.z100.cocktailshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleApiRequestException(ApiException e, Model model) {

		ApiError error = new ApiError(
				e.getMessage(),
				HttpStatus.BAD_REQUEST,
				null,
				ZonedDateTime.now(ZoneId.of("Z"))
		);

		model.addAttribute("errorMessage", error.message());

		return "/misc/error";
	}
}
