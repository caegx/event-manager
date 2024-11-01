package com.example.EventVenueManagement.controller;

import com.example.EventVenueManagement.request.RegisterUserRequest;
import com.example.EventVenueManagement.response.RegisterUserResponse;
import com.example.EventVenueManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
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
}
