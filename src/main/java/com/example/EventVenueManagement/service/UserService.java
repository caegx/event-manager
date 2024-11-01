package com.example.EventVenueManagement.service;


import com.example.EventVenueManagement.repository.UserRepository;
import com.example.EventVenueManagement.request.RegisterUserRequest;
import com.example.EventVenueManagement.response.RegisterUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    public RegisterUserResponse register(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            logger.info("Email already exists: {}", request.getEmail());
            throw new RuntimeException("Email already exists");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            logger.info("Password confirmation does not match for {}", request.getEmail());
            throw new RuntimeException("Password confirmation does not match");
        }

        return new RegisterUserResponse(true, "User successfully", request.getEmail());
    }
}
