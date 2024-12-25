package com.example.pacmazeAdventures.services;

import com.example.pacmazeAdventures.DTO.SignInDTO.UserSignInRequest;
import com.example.pacmazeAdventures.DTO.SignInDTO.UserSignInResponse;
import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpRequest;
import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpResponse;
import com.example.pacmazeAdventures.entity.User;
import com.example.pacmazeAdventures.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserSignUpResponse registerUser(UserSignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return new UserSignUpResponse("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return new UserSignUpResponse("Email is already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hashing the password
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return new UserSignUpResponse("User registered successfully");
    }

    public UserSignInResponse verifyUser(UserSignInRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            return null;
        }

        // Verify password using BCryptPasswordEncoder
        if (passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return new UserSignInResponse("Sign In Successfull");
        }

        return null;
    }
}
