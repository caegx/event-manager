package com.example.EventVenueManagement.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyUserRequest {
    private String verificationCode;
}
