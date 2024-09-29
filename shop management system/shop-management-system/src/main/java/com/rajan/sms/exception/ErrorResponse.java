/**
 * 
 */
package com.rajan.sms.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * com.rajan.sms.exception
 * 
 * @author Rajan kumar
 */
@Getter
@Setter
public class ErrorResponse {

	private LocalDateTime timestamp;
    private int statusCode;
    private String message;
    private String details;
    private String path;
    
    public ErrorResponse(int statusCode,String message,String details,String path)
    {
    	this.timestamp=LocalDateTime.now();
    	this.statusCode=statusCode;
    	this.message=message;
    	this.details=details;
    	this.path=path;
    	
    }

}
