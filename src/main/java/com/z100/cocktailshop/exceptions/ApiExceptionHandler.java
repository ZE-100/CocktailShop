package com.z100.cocktailshop.exceptions;

import com.z100.cocktailshop.util.Logger;
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

		Logger.getInstance().log(e);

		ApiError error = new ApiError(
				e.getMessage(),
				HttpStatus.BAD_REQUEST,
				null,
				ZonedDateTime.now(ZoneId.of("Z"))
		);

		model.addAttribute("errorMessage", error.message());

		return "/misc/error";
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleRuntimeException(RuntimeException e, Model model) {

		Logger.getInstance().log(e);

		ApiError error = new ApiError(
				e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR,
				null,
				ZonedDateTime.now(ZoneId.of("Z"))
		);

		model.addAttribute("errorMessage", error.message());

		return "/misc/error";
	}

	@ExceptionHandler(LoggerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleRuntimeException(LoggerException e, Model model) {

		ApiError error = new ApiError(
				e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR,
				null,
				ZonedDateTime.now(ZoneId.of("Z"))
		);

		model.addAttribute("errorMessage", error.message());

		return "/misc/error";
	}
}
