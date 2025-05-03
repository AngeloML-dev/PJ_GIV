package com.lozano.domain.repository;

import com.lozano.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IRoleRepository extends JpaRepository<Role, String> {
    Set<Role> findByNameIn(Set<String> names); // Buscar roles por nombre
}
