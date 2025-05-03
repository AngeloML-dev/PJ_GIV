package com.lozano.application.service;

import com.lozano.domain.entity.Role;

import java.util.List;

public interface IRoleService {
    Role saveRole(Role role);
    Role findRoleByName(String name);
    List<Role> findAllRoles();
}
