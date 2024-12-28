package com.example.pacmazeAdventures.controller;


import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpRequest;
import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpResponse;
import com.example.pacmazeAdventures.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponse> signUp(@RequestBody UserSignUpRequest request) {
        UserSignUpResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }


}