package com.lozano.application.mapper;

import com.lozano.application.dto.Role.RoleRequestDTO;
import com.lozano.application.dto.Role.RoleResponseDTO;
import com.lozano.domain.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleResponseDTO toResponseDTO(Role role) {
        if (role == null) return null;
        return RoleResponseDTO.builder()
                .name(role.getName())
                .build();
    }

    public Role toEntity(RoleRequestDTO dto) {
        if (dto == null) return null;
        return Role.builder()
                .name(dto.getName())
                .build();
    }
}
