package org.aura.plateforme_iot.Mapper;

import org.aura.plateforme_iot.dto.UserDto;
import org.aura.plateforme_iot.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRolesToStrings(user.getRoles()))")
    UserDto toDTO(org.aura.plateforme_iot.entity.User user);

    @Mapping(target = "roles", expression = "java(mapStringsToRoles(userDTO.getRoles()))")
    org.aura.plateforme_iot.entity.User toEntity(UserDto userDTO);

    default List<String> mapRolesToStrings(List<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }

    default List<Role> mapStringsToRoles(List<String> roleNames) {
        // Si vous avez un service ou un référentiel pour charger les rôles, utilisez-le ici.
        return roleNames.stream().map(name -> {
            Role role = new Role();
            role.setName(name); // On définit uniquement le nom ici
            return role;
        }).collect(Collectors.toList());
    }
}
