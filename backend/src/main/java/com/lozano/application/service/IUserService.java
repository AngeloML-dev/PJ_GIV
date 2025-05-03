package com.lozano.application.service;

import com.lozano.application.dto.User.UserRequestDTO;
import com.lozano.application.dto.User.UserResponseDTO;
import com.lozano.domain.entity.User;

import java.util.List;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> listAll();
    UserResponseDTO findById(Long id);
    UserResponseDTO create(UserRequestDTO dto);
    void delete(Long id);
}
