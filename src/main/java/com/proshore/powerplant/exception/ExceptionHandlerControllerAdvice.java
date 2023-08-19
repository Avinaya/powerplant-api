package com.proshore.powerplant.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

/**
 * Global controller advice to handle exceptions and provide standardized error responses.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerControllerAdvice {

    /**
     * Exception handler for ResourceNotFoundException.
     *
     * @param exception The ResourceNotFoundException that was thrown.
     * @param request   The HttpServletRequest associated with the request.
     * @return An ErrorMessage containing details about the error.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorMessage handleResourceNotFound(
            final ResourceNotFoundException exception,
            final HttpServletRequest request) {
        return ErrorMessage.builder()
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .timestamp(Instant.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
    }

    /**
     * Exception handler method to handle MethodArgumentNotValidException.
     * This method is responsible for handling validation errors in request arguments.
     *
     * @param exception The MethodArgumentNotValidException thrown during request validation.
     * @param request   The HttpServletRequest containing information about the request.
     * @return An ErrorMessage object containing details about the error response.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorMessage handleMethodArgumentNotValid(
            final ConstraintViolationException exception,
            final HttpServletRequest request) {
        return ErrorMessage.builder()
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .timestamp(Instant.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    /**
     * Generic exception handler for all other exceptions.
     *
     * @param exception The Exception that was thrown.
     * @param request   The HttpServletRequest associated with the request.
     * @return An ErrorMessage containing details about the error.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorMessage handleException(
            final Exception exception,
            final HttpServletRequest request) {
        return ErrorMessage.builder()
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .timestamp(Instant.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

}
