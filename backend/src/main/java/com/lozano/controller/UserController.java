package com.lozano.controller;

import com.lozano.application.dto.User.UserRequestDTO;
import com.lozano.application.dto.User.UserResponseDTO;
import com.lozano.application.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public List<UserResponseDTO> listar() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<UserResponseDTO> getUsersByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO updatedUser = userService.update(id, requestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO requestDTO){
        UserResponseDTO createdUser = userService.create(requestDTO);
        return ResponseEntity.status(201).body(createdUser);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String name) {
        userService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }


}
