/**
 * 
 */
package com.rajan.sms.util;

/**
 * com.rajan.sms.util DelL
 */
public class ErrorMessage {
	public static String getResourceNotFoundMessage(String resourceName, Long id) {
		return resourceName + " with ID " + id + " could not be found.";
	}

}
