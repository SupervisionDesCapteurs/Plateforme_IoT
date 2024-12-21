package org.aura.plateforme_iot.Mapper;


import org.aura.plateforme_iot.dto.UserDto.UserDTO;
import org.aura.plateforme_iot.dto.UserDto.UserResponseDTO;
import org.aura.plateforme_iot.entity.Role;
import org.aura.plateforme_iot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRolesToStrings(user.getRoles()))")
    UserResponseDTO toDTO(User user);

    @Mapping(target = "roles", ignore = true)
    User toEntity(UserDTO userDTO);

    default List<String> mapRolesToStrings(List<Role> roles) {
        return roles.stream().map(Role::getName).toList();
    }
}
