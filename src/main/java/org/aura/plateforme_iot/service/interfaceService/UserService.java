package org.aura.plateforme_iot.service.interfaceService;

import org.aura.plateforme_iot.dto.UserDto;
import org.aura.plateforme_iot.entity.User;

import java.util.List;

public interface UserService {
    UserDto register(UserDto userDTO);
    List<UserDto> getAllUsers();
    void updateUserRoles(String id, List<String> roles);
    User loadUserByUsername(String username);
}
