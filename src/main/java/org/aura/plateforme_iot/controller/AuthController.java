package org.aura.plateforme_iot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aura.plateforme_iot.dto.LoginRequest;
import org.aura.plateforme_iot.Security.JwtTokenProvider;
import org.aura.plateforme_iot.dto.AuthResponse;
import org.aura.plateforme_iot.dto.UserDto;
import org.aura.plateforme_iot.service.CustomUserDetails;
import org.aura.plateforme_iot.service.interfaceService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {


        log.info("User: {} Pass: {}", request.getUsername(), request.getPassword());
//        System.out.println(request.getUsername() + " " + request.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(userDetails.getUsername());
//
        return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
//        return ResponseEntity.ok(null);
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto userDTO) {
        UserDto registeredUser = userService.register(userDTO);
        String token = jwtTokenProvider.generateToken(registeredUser.getUsername());
        List<SimpleGrantedAuthority> authorities = registeredUser.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthResponse(token, registeredUser.getUsername(), authorities));
    }
}