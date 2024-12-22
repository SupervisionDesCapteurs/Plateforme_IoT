package org.aura.plateforme_iot.controller;

import lombok.AllArgsConstructor;
import org.aura.plateforme_iot.Security.JwtTokenProvider;
import org.aura.plateforme_iot.dto.UserDto.AuthResponse;
import org.aura.plateforme_iot.dto.UserDto.LoginRequest;
import org.aura.plateforme_iot.dto.UserDto.UserDTO;
import org.aura.plateforme_iot.dto.UserDto.UserResponseDTO;
import org.aura.plateforme_iot.service.CustomUserDetails;
import org.aura.plateforme_iot.service.TokenBlacklistService;
import org.aura.plateforme_iot.service.interfaceService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .collect(Collectors.toList());

        String token = jwtTokenProvider.generateToken(userDetails.getUsername(), roles);

        return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDTO userDTO) {
        UserResponseDTO registeredUser = userService.register(userDTO);

        List<String> roles = registeredUser.getRoles(); // Assuming roles are a list of strings
        String token = jwtTokenProvider.generateToken(registeredUser.getUsername(), roles);

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthResponse(token, registeredUser.getUsername(), authorities));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");


        tokenBlacklistService.blacklistToken(token);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}