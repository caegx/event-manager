package com.example.EventVenueManagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VerifyUserResponse {
    private boolean success;
    private String message;
}
