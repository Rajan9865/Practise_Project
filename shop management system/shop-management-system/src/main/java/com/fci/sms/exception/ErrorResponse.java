/**
 * 
 */
package com.fci.sms.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * com.fci.sms.exception
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
    	this.timestamp=timestamp;
    	this.statusCode=statusCode;
    	this.message=message;
    	this.details=details;
    	this.path=path;
    	
    }

}
