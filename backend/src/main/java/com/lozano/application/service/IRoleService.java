package com.lozano.application.service;

import com.lozano.application.dto.Role.RoleRequestDTO;
import com.lozano.application.dto.Role.RoleResponseDTO;
import com.lozano.domain.entity.Role;

import java.util.List;

public interface IRoleService {
    RoleResponseDTO create(RoleRequestDTO dto);
    RoleResponseDTO update(String name, RoleRequestDTO dto);
    RoleResponseDTO findByName(String name);
    RoleResponseDTO findById(String id);
    List<RoleResponseDTO> findAll();
    void deleteByName(String name);
    void deleteById(String id);
}
