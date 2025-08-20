package com.example.demo.exception;

public class TemplateValidationException extends RuntimeException {
	 
    public TemplateValidationException(String message) {
        super(message);
    }

    public TemplateValidationException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
