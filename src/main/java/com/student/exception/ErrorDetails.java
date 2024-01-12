package com.student.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails 
{
	private LocalDateTime timestamp;
	
	private String errorMessage;
	
	private String details;
}