/**
 * 
 */
package com.fci.sms.exception;

/**
 * com.fci.sms.exception
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
