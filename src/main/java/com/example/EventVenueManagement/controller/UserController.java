package com.example.EventVenueManagement.controller;

import com.example.EventVenueManagement.request.RegisterUserRequest;
import com.example.EventVenueManagement.request.VerifyUserRequest;
import com.example.EventVenueManagement.response.RegisterUserResponse;
import com.example.EventVenueManagement.response.VerifyUserResponse;
import com.example.EventVenueManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;


    @PostMapping("register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
        var response = service.register(request);

        if (response.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("verify")
    public ResponseEntity<VerifyUserResponse> verifyUser(@RequestBody VerifyUserRequest request) {
        var response = service.verifyUser(request);

        if (response.isSuccess())
            return ResponseEntity.status(HttpStatus.OK).body(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("resend-code")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
        service.resendVerificationCode(email);
        return
    }
}
