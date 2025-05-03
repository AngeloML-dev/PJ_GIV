package com.lozano.application.mapper;

import com.lozano.application.dto.User.UserRequestDTO;
import com.lozano.application.dto.User.UserResponseDTO;
import com.lozano.domain.entity.Role;
import com.lozano.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {


    public UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(
                        user.getRoles().stream()
                                .map(Role::getName)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public User toEntity(UserRequestDTO dto, Set<Role> roles) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(roles)
                .build();
    }
}
