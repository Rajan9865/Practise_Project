/**
 * 
 */
package com.rajan.sms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.rajan.sms.util.ErrorMessage;

import jakarta.servlet.http.HttpServletRequest;

/**
 * com.rajan.sms.exception
 * 
 * @author Rajan kumar
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
			HttpServletRequest request) {
		String detail=ErrorMessage.getResourceNotFoundMessage(ex.getResourceName(), ex.getResourceId());
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(), 
				ex.getMessage(),
				detail,
				request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// Handle insufficient stock exceptions
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException ex,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(),
				request.getDescription(false), 
				request.getContextPath());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// Handle invalid request exceptions
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(),
				request.getDescription(false), 
				request.getContextPath());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorResponse>handleCategoryNotFound(CategoryNotFoundException ex,WebRequest request)
	{
		ErrorResponse errorResponse=new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				request.getDescription(false),
				request.getContextPath());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCategoryException.class)
	public ResponseEntity<ErrorResponse>handleInvalidCategory(InvalidCategoryException exception,WebRequest request)
	{
		ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exception.getMessage(),
				request.getDescription(false),
				request.getContextPath());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	// Handle all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				"An unexpected error occurred.",
				request.getDescription(false), 
				request.getContextPath());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
