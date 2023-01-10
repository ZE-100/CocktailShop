package com.z100.cocktailshop.exceptions;

import lombok.Getter;

public class LoggerException extends RuntimeException {

	@Getter
	private String message;

	public LoggerException() {
		super();
	}

	public LoggerException(String message) {
		this.message = message;
	}

	public LoggerException(String message, Throwable cause) {
		super(message, cause);
	}
}
