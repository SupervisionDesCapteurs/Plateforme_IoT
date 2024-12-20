package org.aura.plateforme_iot.controller;

import org.aura.plateforme_iot.dto.UserDto;
import org.aura.plateforme_iot.service.interfaceService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateUserRoles(@PathVariable String id, @RequestBody List<String> roles) {
        userService.updateUserRoles(id, roles);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}