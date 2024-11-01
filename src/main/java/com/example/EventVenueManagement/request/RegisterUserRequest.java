package com.example.EventVenueManagement.request;

import com.example.EventVenueManagement.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    private String email;
    private Role role;
    private String password;
    private String confirmPassword;
}
