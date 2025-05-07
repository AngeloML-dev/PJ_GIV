package com.lozano.application.service.impl;

import com.lozano.application.dto.Role.RoleRequestDTO;
import com.lozano.application.dto.Role.RoleResponseDTO;
import com.lozano.application.mapper.RoleMapper;
import com.lozano.application.service.IRoleService;
import com.lozano.domain.entity.Role;
import com.lozano.domain.repository.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(IRoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponseDTO create(RoleRequestDTO dto) {
        // Se valida el rol
        if (dto == null || dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio y no puede estar vacío.");
        }

        // Se verifica si hay duplicidad
        if (roleRepository.existsById(dto.getName())) {
            throw new IllegalStateException("Ya existe un rol con el nombre: " + dto.getName());
        }

        Role entity = roleMapper.toEntity(dto);
        return roleMapper.toResponseDTO(roleRepository.save(entity));
    }

    @Override
    public RoleResponseDTO update(String name, RoleRequestDTO dto) {
        // Validación
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre original del rol no puede estar vacío.");
        }

        if (dto == null || dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("El nuevo nombre del rol no puede estar vacío.");
        }

        // Verificación
        if (!name.equals(dto.getName())) {
            throw new IllegalStateException("No se permite cambiar el nombre del rol.");
        }

        Role existing = roleRepository.findById(name)
                .orElseThrow(() -> new IllegalStateException("No se encontró un rol con el nombre: " + name));

        // No hay atributos adicionales que actualizar, pero se puede reescribir el mismo nombre.
        return roleMapper.toResponseDTO(roleRepository.save(existing));
    }

    @Override
    public RoleResponseDTO findByName(String name) {
        // Validación
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío.");
        }

        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new IllegalStateException("No se encontró un rol con el nombre: " + name));

        return roleMapper.toResponseDTO(role);
    }

    @Override
    public RoleResponseDTO findById(String id) {
        return findByName(id); // Alias
    }

    @Override
    public List<RoleResponseDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteByName(String name) {
        // Validación
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío.");
        }

        // Verificación
        if (!roleRepository.existsById(name)) {
            throw new IllegalStateException("No se encontró un rol con el nombre: " + name);
        }

        roleRepository.deleteById(name);
    }

    @Override
    public void deleteById(String id) {
        deleteByName(id); // Alias
    }
}
