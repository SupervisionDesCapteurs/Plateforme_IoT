package org.aura.plateforme_iot.service.interfaceService;

import org.aura.plateforme_iot.dto.UserDto.UserDTO;
import org.aura.plateforme_iot.dto.UserDto.UserResponseDTO;
import org.aura.plateforme_iot.entity.User;

import java.util.List;

public interface UserService {
    UserResponseDTO register(UserDTO userDTO);
    List<UserResponseDTO> getAllUsers();
    void updateUserRoles(String id, List<String> roles);
    User loadUserByUsername(String username);
}

