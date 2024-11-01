package com.example.EventVenueManagement.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;
    private String role;
    private String token;

}
