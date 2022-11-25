package com.z100.cocktailshop.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiError(String message, HttpStatus status, Throwable cause, ZonedDateTime timestamp) {}
