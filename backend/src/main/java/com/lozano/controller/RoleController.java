package com.lozano.controller;

import com.lozano.application.dto.Role.RoleRequestDTO;
import com.lozano.application.dto.Role.RoleResponseDTO;
import com.lozano.application.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService service;

    // Listar todos los roles
    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleResponseDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Crear un nuevo rol
    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponseDTO> create(@RequestBody RoleRequestDTO dto) {
        RoleResponseDTO saved = service.create(dto);
        return ResponseEntity
                .created(URI.create("/api/roles/" + saved.getName()))
                .body(saved);
    }

    // Obtener un rol por su nombre
    @GetMapping("/{name}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponseDTO> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    // Obtener un rol por su ID
    @GetMapping("/id/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // Actualizar un rol
    @PutMapping("/{name}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponseDTO> update(
            @PathVariable String name,
            @RequestBody RoleRequestDTO dto) {
        return ResponseEntity.ok(service.update(name, dto));
    }

    // Eliminar un rol por nombre
    @DeleteMapping("/{name}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        service.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    // Eliminar un rol por ID
    @DeleteMapping("/id/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
