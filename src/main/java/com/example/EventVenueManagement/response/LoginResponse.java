package com.example.EventVenueManagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;
    private String role;
    private String token;

}
