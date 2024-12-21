package org.aura.plateforme_iot.service.ServiceImplementation;

import lombok.AllArgsConstructor;
import org.aura.plateforme_iot.Mapper.UserMapper;
import org.aura.plateforme_iot.dto.UserDto.UserDTO;
import org.aura.plateforme_iot.dto.UserDto.UserResponseDTO;
import org.aura.plateforme_iot.entity.Role;
import org.aura.plateforme_iot.entity.User;
import org.aura.plateforme_iot.exception.ResourceNotFoundException;
import org.aura.plateforme_iot.repository.RoleRepository;
import org.aura.plateforme_iot.repository.UserRepository;
import org.aura.plateforme_iot.service.interfaceService.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserImpl  implements UserService {

    private UserRepository userRepository;


    private RoleRepository roleRepository;


    private PasswordEncoder passwordEncoder;


    private UserMapper userMapper;

    @Override
    public UserResponseDTO register(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = userDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName))
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            roles.add(roleRepository.findByName("USER"));
        }

        user.setRoles(roles);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public void updateUserRoles(String id, List<String> roles) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<Role> updatedRoles = roles.stream().map(roleRepository::findByName).collect(Collectors.toList());
        user.setRoles(updatedRoles);
        userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }
}