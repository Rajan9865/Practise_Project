/**
 * 
 */
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * @author deby7
 *7:56:18 am
 *2024
 *demo
 *TODO
 */
@Getter
@Setter
 
// @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResoucreNotFoundException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 String resourceName;
	 String filedName;
	 long fieldValue;
	 
	/**
	 * @param resourceName
	 * @param filedName
	 * @param 
	 */
	public ResoucreNotFoundException(String resourceName, String filedName, Integer employeeid) {
		super(String.format("%s not found with %s : %s",resourceName,filedName,employeeid ));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue = employeeid;
	}

}
