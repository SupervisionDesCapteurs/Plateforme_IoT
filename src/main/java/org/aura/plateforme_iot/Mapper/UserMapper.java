package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.UserDto;
import org.aura.plateforme_iot.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "roles", expression = "java(mapRolesToStrings(user.getRoles()))")
    UserDto toDTO(org.aura.plateforme_iot.entity.User user);

//    @Mapping(target = "roles", ignore = true)
    org.aura.plateforme_iot.entity.User toEntity(UserDto userDTO);

    default List<String> mapRolesToStrings(List<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
