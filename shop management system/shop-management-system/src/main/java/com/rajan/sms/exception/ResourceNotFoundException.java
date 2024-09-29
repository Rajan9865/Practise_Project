/**
 * 
 */
package com.rajan.sms.exception;

import lombok.Getter;

/**
 * com.rajan.sms.exception
 * 
 * @author Rajan kumar
 */
@Getter
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String resourceName;
    private final Long resourceId;
	public ResourceNotFoundException(String resourceName, Long resourceId) {
		super(resourceName + " with ID " + resourceId + " not found");
		this.resourceName=resourceName;
		this.resourceId=resourceId;
	}
}
