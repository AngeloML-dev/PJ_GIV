package com.lozano.application.service;

import com.lozano.application.dto.User.UserRequestDTO;
import com.lozano.application.dto.User.UserResponseDTO;
import com.lozano.domain.entity.User;

import java.util.List;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> findAll();
    UserResponseDTO findById(Long id);
    List<UserResponseDTO> findByName(String name);
    UserResponseDTO create(UserRequestDTO dto);
    void deleteById(Long id);
    void deleteByName(String name);
    UserResponseDTO update(Long id, UserRequestDTO dto);
}
