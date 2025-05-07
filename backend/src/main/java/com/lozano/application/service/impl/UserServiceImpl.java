package com.lozano.application.service.impl;

import com.lozano.application.dto.User.UserRequestDTO;
import com.lozano.application.dto.User.UserResponseDTO;
import com.lozano.application.mapper.RoleMapper;
import com.lozano.application.mapper.UserMapper;
import com.lozano.application.service.IUserService;
import com.lozano.domain.entity.Role;
import com.lozano.domain.entity.User;
import com.lozano.domain.repository.IRoleRepository;
import com.lozano.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No se encontraron usuarios");
        }
        return users.stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de usuario no válido");
        }
        return userRepository.findById(id)
                .map(userMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<UserResponseDTO> findByName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        Optional<User> userOptional = userRepository.findByUsername(name);
        if (userOptional.isPresent()) {
            return List.of(userMapper.toResponseDTO(userOptional.get()));
        } else {
            throw new RuntimeException("No se encontró ningún usuario con el nombre: " + name);
        }
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        validateUserRequestDTO(dto);

        Set<Role> roles = roleRepository.findByNameIn(dto.getRoles());
        if (roles.isEmpty()) {
            throw new RuntimeException("Uno o más roles no son válidos");
        }

        User user = userMapper.toEntity(dto, roles);
        userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de usuario no válido");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        validateUserRequestDTO(dto);

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        Set<Role> roles = roleRepository.findByNameIn(dto.getRoles());
        if (roles.isEmpty()) {
            throw new RuntimeException("Uno o más roles no son válidos");
        }
        user.setRoles(roles);

        userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de usuario no válido");
        }
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de usuario no válido");
        }

        Optional<User> userOptional = userRepository.findByUsername(name);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        userRepository.deleteByUsername(name);
    }


    private void validateUserRequestDTO(UserRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de usuario no puede ser nulo");
        }
        if (!StringUtils.hasText(dto.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        if (!StringUtils.hasText(dto.getPassword())) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (dto.getRoles() == null || dto.getRoles().isEmpty()) {
            throw new IllegalArgumentException("El usuario debe tener al menos un rol");
        }
    }
}
