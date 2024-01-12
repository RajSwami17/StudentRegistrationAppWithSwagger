package com.student.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseEntityExceptionHandler 
{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception 
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleStudentNotFoundException(StudentNotFoundException snfe, WebRequest request)throws Exception 
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), snfe.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailInUseException.class)
	public final ResponseEntity<ErrorDetails> handleEmailInUseException(EmailInUseException eiue, WebRequest request) throws Exception 
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), eiue.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(EmptyInputException.class)
	public final ResponseEntity<ErrorDetails> handleEmptyInputException(EmptyInputException eie, WebRequest request) throws Exception 
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), eie.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleSubjectNotFoundException(CourseNotFoundException cnfe, WebRequest request)throws Exception 
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), cnfe.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
