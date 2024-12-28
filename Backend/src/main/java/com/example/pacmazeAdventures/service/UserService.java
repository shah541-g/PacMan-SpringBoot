package com.example.pacmazeAdventures.service;

import com.example.pacmazeAdventures.DTO.SignInDTO.UserSignInRequest;
import com.example.pacmazeAdventures.DTO.SignInDTO.UserSignInResponse;
import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpRequest;
import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpResponse;
import com.example.pacmazeAdventures.DTO.UserDetailsDTO.UserDetailsDTO;
import com.example.pacmazeAdventures.entity.User;
import com.example.pacmazeAdventures.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // List to track signed-in users (using CopyOnWriteArrayList for thread-safety)
    private static List<String> signedInUsers = new CopyOnWriteArrayList<>();


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

    public void signOut(String email) {
        // Remove the user from the signed-in users list
        System.out.println(email);
        signedInUsers.remove(email);
    }

    public boolean isUserSignedIn(String email) {
        return signedInUsers.contains(email);
    }

    public UserSignInResponse verifyUser(UserSignInRequest request) {

        if(signedInUsers.contains(request.getEmail())){
            return new UserSignInResponse("already Signed In");
        }

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            return null;
        }

        // Verify password using BCryptPasswordEncoder
        if (passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            // Add to signed-in users list
            signedInUsers.add(request.getEmail());
            return new UserSignInResponse("Sign In Successfull");
        }

        return null;
    }

    public UserDetailsDTO getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Convert User to UserDTO
            return new UserDetailsDTO(user.getEmail(), user.getUsername());
        } else {
            // Return null or throw an exception if user not found
            return null;
        }
    }
}
