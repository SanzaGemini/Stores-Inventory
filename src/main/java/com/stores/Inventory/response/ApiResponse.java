package com.stores.Inventory.response;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * A generic API response class to standardize the structure of API responses.
 * It supports success, error, and failure scenarios.
 *
 * @param <T> The type of data being returned in the response.
 */
@Getter
@Setter
public class ApiResponse<T> {

    private String status;  // Status of the response (e.g., success, error, failed)
    private String message;  // A message related to the status of the response
    private T data;  // The actual data of the response, can be any type

    // Default constructor
    public ApiResponse() {}

    // Constructor for error responses with error messages
    @SuppressWarnings("unchecked")
    public ApiResponse(String status, Map<String,String> errorMessages) {
        this.status = status;              // e.g., "error"
        this.message = "There was an error processing the request";  // or a more specific message
        this.data = (T) errorMessages;         // Assign the errorMessages directly without casting
    }
    

    // Constructor for success responses with data
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    // Static method for a successful response
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", "Request was successful", data);
    }


    // Static method for an error response with a map of error messages
    public static ApiResponse<Object> error(Map<String,String> errorMessages) {
        return new ApiResponse<>("error", errorMessages);
    }

    // Static method for a failure response with a message
    public static ApiResponse<Object> failure(String message) {
        return new ApiResponse<>("failed", message, null);
    }

    @Override
    public String toString() {
        if(data != null){
            return "{status=" + status + ", message=" + message + ", data=" + data + "}";
        } return "{status=" + status + ", message=" + message + "}";
    }

    
}