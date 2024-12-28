package com.example.pacmazeAdventures.controller;


import com.example.pacmazeAdventures.DTO.UserDetailsDTO.UserDetailsDTO;
import com.example.pacmazeAdventures.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @GetMapping("/my-details")
    public ResponseEntity<UserDetailsDTO> getUserByEmail(@RequestParam String email) {
        UserDetailsDTO userDTO = userService.getUserByEmail(email);

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
