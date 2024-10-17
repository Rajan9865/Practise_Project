/**
 * 
 */
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
// @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResoucreNotFoundException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	 String resourceName;
	 String filedName;
	 long fieldValue;
}
