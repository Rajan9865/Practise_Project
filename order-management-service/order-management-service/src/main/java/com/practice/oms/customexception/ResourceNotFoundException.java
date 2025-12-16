package com.practice.oms.customexception;


/**
 * ResourceNotFoundException
 *
 * <p>
 * Thrown when a requested resource
 * is not found in the database.
 * </p>
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}