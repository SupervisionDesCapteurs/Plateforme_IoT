package org.aura.plateforme_iot;

import org.aura.plateforme_iot.Security.JwtTokenProvider;
import org.aura.plateforme_iot.controller.AuthController;
import org.aura.plateforme_iot.dto.AuthResponse;
import org.aura.plateforme_iot.dto.LoginRequest;
import org.aura.plateforme_iot.dto.UserDto;
import org.aura.plateforme_iot.service.CustomUserDetails;
import org.aura.plateforme_iot.service.interfaceService.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerSecurityTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");

        CustomUserDetails userDetails = new CustomUserDetails(
                "testUser",
                "testPass",
                true, // Active account
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtTokenProvider.generateToken("testUser")).thenReturn("mockedToken");

        // Act
        ResponseEntity<AuthResponse> response = authController.login(loginRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("mockedToken", response.getBody().getToken());
        assertEquals("testUser", response.getBody().getUsername());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider).generateToken("testUser");
    }

    @Test
    void testRegister_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("newUser");
        userDto.setPassword("newPass");
        userDto.setRoles(List.of("ROLE_USER"));

        UserDto registeredUser = new UserDto();
        registeredUser.setUsername("newUser");
        registeredUser.setRoles(List.of("ROLE_USER"));

        when(userService.register(userDto)).thenReturn(registeredUser);
        when(jwtTokenProvider.generateToken("newUser")).thenReturn("mockedToken");

        // Act
        ResponseEntity<AuthResponse> response = authController.register(userDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("mockedToken", response.getBody().getToken());
        assertEquals("newUser", response.getBody().getUsername());
        verify(userService).register(userDto);
        verify(jwtTokenProvider).generateToken("newUser");
    }
}
