package com.example.pacmazeAdventures;

import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpRequest;
import com.example.pacmazeAdventures.DTO.SignUpDTO.UserSignUpResponse;
import com.example.pacmazeAdventures.controller.SignUpController;
import com.example.pacmazeAdventures.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignUpControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private SignUpController signUpController;

	@Test
	void testSignUp() {
		// Arrange
		UserSignUpRequest request = new UserSignUpRequest();
		request.setUsername("testUser");
		request.setPassword("testPassword");

		UserSignUpResponse mockResponse = new UserSignUpResponse();
		mockResponse.setMessage("User registered successfully");

		when(userService.registerUser(any(UserSignUpRequest.class))).thenReturn(mockResponse);

		// Act
		ResponseEntity<UserSignUpResponse> response = signUpController.signUp(request);

		// Assert
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("User registered successfully", response.getBody().getMessage());
		Mockito.verify(userService, Mockito.times(1)).registerUser(any(UserSignUpRequest.class));
	}
}
