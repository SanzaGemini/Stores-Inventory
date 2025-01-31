package com.stores.Inventory.Unittest;

import org.junit.jupiter.api.Test;

import com.stores.Inventory.response.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseTest {

    // Test for success constructor
    @Test
    public void testSuccessResponse() {
        // Given
        String expectedStatus = "success";
        String expectedMessage = "Request was successful";
        String expectedData = "Test data";

        // When
        ApiResponse<String> response = ApiResponse.success(expectedData);

        // Then
        assertEquals(expectedStatus, response.getStatus());
        assertEquals(expectedMessage, response.getMessage());
        assertEquals(expectedData, response.getData());
    }

    // Test for error constructor with Map of error messages
    @Test
    public void testErrorResponse() {
        // Given
        Map<String, String> errorMessages = new HashMap<>();
        errorMessages.put("error1", "Invalid input");
        errorMessages.put("error2", "Field required");

        // When
        ApiResponse<Object> response = ApiResponse.error(errorMessages);

        // Then
        assertEquals("error", response.getStatus());
        assertEquals("There was an error processing the request", response.getMessage());
        assertEquals(errorMessages, response.getData());
    }

    // Test for failure constructor with failure message
    @Test
    public void testFailureResponse() {
        // Given
        String failureMessage = "Something went wrong";

        // When
        ApiResponse<Object> response = ApiResponse.failure(failureMessage);

        // Then
        assertEquals("failed", response.getStatus());
        assertEquals(failureMessage, response.getMessage());
        assertNull(response.getData());
    }

    // Test for default constructor (no arguments)
    @Test
    public void testDefaultConstructor() {
        // When
        ApiResponse<String> response = new ApiResponse<>();

        // Then
        assertNull(response.getStatus());
        assertNull(response.getMessage());
        assertNull(response.getData());
    }

    // Test for toString() method with data
    @Test
    public void testToStringWithData() {
        // Given
        ApiResponse<String> response = ApiResponse.success("Test data");

        // When
        String result = response.toString();

        // Then
        assertTrue(result.contains("status=success"));
        assertTrue(result.contains("message=Request was successful"));
        assertTrue(result.contains("data=Test data"));
    }

    // Test for toString() method without data
    @Test
    public void testToStringWithoutData() {
        // Given
        ApiResponse<Object> response = ApiResponse.failure("Failure message");

        // When
        String result = response.toString();

        // Then
        assertTrue(result.contains("status=failed"));
        assertTrue(result.contains("message=Failure message"));
        assertFalse(result.contains("data"));
    }
}
