package com.example.EventVenueManagement.service;


import com.example.EventVenueManagement.model.User;
import com.example.EventVenueManagement.repository.UserRepository;
import com.example.EventVenueManagement.request.RegisterUserRequest;
import com.example.EventVenueManagement.response.RegisterUserResponse;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

import static com.example.EventVenueManagement.model.Role.EVENT_PLANNER;
import static com.example.EventVenueManagement.model.Role.VENUE_OWNER;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    public RegisterUserResponse register(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            logger.info("Email already exists: {}", request.getEmail());
            return new RegisterUserResponse(false, "Email already exists", request.getEmail());
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            logger.info("Password confirmation does not match for {}", request.getEmail());
            return new RegisterUserResponse(false, "Passwords do not match", request.getEmail());
        }
        var user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(15));
        user.setAccountVerified(false);
        switch (request.getRole()) {
            case "VENUE OWNER":
                user.setRole(VENUE_OWNER);
                break;
            case "EVENT PLANNER":
                user.setRole(EVENT_PLANNER);
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }

        sendVerificationEmail(user);
        logger.info("Verification code sent to {}", user.getEmail());
        userRepository.save(user);

        return new RegisterUserResponse(true, "User successfully", request.getEmail());
    }

    private String generateVerificationCode() {
        var random = new Random();
        int code = random.nextInt(900_000) + 100_000;
        return String.valueOf(code);
    }

    private void sendVerificationEmail(User user) {
        String subject = "Account Verification";
        String verificationCode = user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }"
                + ".container { width: 100%; max-width: 600px; margin: auto; padding: 20px; }"
                + ".header { text-align: center; padding: 20px; background-color: #007bff; color: white; border-radius: 5px 5px 0 0; }"
                + ".content { background-color: white; padding: 20px; border-radius: 0 0 5px 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
                + ".verification-code { font-size: 20px; font-weight: bold; color: #007bff; text-align: center; }"
                + ".footer { text-align: center; margin-top: 20px; font-size: 14px; color: #777; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"container\">"
                + "<div class=\"header\">"
                + "<h2>Welcome to Our App!</h2>"
                + "</div>"
                + "<div class=\"content\">"
                + "<p>Please enter the verification code below to continue:</p>"
                + "<div class=\"verification-code\">" + verificationCode + "</div>"
                + "</div>"
                + "<div class=\"footer\">"
                + "<p>If you didn't request this email, please ignore it.</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try{
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        }catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
