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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        Set<Role> roles = roleRepository.findByNameIn(userRequestDTO.getRoles()); // Obtener roles del repositorio
        User user = userMapper.toEntity(userRequestDTO, roles);  // Llamada con roles
        userRepository.save(user);
        return userMapper.toResponseDTO(user); // Convertir a DTO para respuesta
    }

    @Override
    public List<UserResponseDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
