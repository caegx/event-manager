package com.example.EventVenueManagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterUserResponse {
    private boolean success;
    private String message;
    private String email;
}
