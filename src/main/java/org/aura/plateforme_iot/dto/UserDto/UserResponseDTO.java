package org.aura.plateforme_iot.dto.UserDto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private String id;

    private String username;

    private String password;

    private Boolean active = true;

    private List<String> roles;
}

