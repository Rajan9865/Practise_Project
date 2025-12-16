package com.practice.oms.customexception;

/**
 * BusinessException
 *
 * <p>
 * Thrown when a business rule is violated.
 * Example: duplicate email.
 * </p>
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}