package com.example.payoneer.network;

// ApiError class to handle response body error
public class ApiError {

    private int statusCode;
    private String message = "Unknown error";


    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
