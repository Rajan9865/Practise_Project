/**
 * 
 */
package com.rajan.sms.exception;

/**
 * com.rajan.sms.exception
 * 
 * @author Rajan kumar
 */
public class InvalidRequestException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRequestException(String message) {
		super(message);
	}
}
