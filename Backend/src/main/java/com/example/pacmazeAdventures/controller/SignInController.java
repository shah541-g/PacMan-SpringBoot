package com.example.pacmazeAdventures.controller;

import com.example.pacmazeAdventures.DTO.SignInDTO.UserSignInRequest;
import com.example.pacmazeAdventures.DTO.SignInDTO.UserSignInResponse;
import com.example.pacmazeAdventures.DTO.SignOutDTO.SignOutDTO;
import com.example.pacmazeAdventures.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class SignInController {

    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody UserSignInRequest request) {
        UserSignInResponse response = userService.verifyUser(request);
        if(response==null || response.getResponse().equals("already Signed In")){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut(@RequestBody SignOutDTO request) {
        userService.signOut(request.getEmail());
        return ResponseEntity.ok(new UserSignInResponse("Sign Out Successful"));
    }
}
