package com.authservice.auth.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.authservice.auth.model.User;
import com.authservice.auth.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void registerUser_unregisteredValidUser_success() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_userAlreadyExists_rejects() throws Exception {
        User user = new User();
        user.setUsername("existingUser");
        user.setPassword("password123");

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User already exists - please log in"));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAuthenticateUser_Success() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(existingUser);
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User authenticated"));

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(passwordEncoder, times(1)).matches("password123", "encodedPassword");
    }

    @Test
    void testAuthenticateUser_InvalidCredentials() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("wrongPassword");

        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(existingUser);
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(passwordEncoder, times(1)).matches("wrongPassword", "encodedPassword");
    }

    @Test
    void testAuthenticateUser_UserNotFound() throws Exception {
        User user = new User();
        user.setUsername("nonExistentUser");
        user.setPassword("password123");

        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));

        verify(userRepository, times(1)).findByUsername("nonExistentUser");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }


}