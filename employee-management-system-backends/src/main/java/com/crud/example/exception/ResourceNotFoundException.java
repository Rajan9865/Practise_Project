/**
 * 
 */
package com.crud.example.exception;

/**
 * com.crud.example.exception DelL
 * @author Rajan kumar
 */
public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
