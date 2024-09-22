/**
 * 
 */
package com.rajan.sms.exception;

/**
 * com.rajan.sms.exception
 * 
 * @author Rajan kumar
 */
public class InsufficientStockException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientStockException(String message) {
		super(message);
	}
}
