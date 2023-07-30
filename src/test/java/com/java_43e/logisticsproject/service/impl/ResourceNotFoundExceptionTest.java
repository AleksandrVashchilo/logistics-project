package com.java_43e.logisticsproject.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
public class ResourceNotFoundExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testDefaultConstructor() {
        ResourceNotFoundException exception = new ResourceNotFoundException();

        assertNull(exception.getMessage());
    }
}
