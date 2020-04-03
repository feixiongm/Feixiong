package com.ascending.repository;

import com.ascending.model.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleById(Long id);
    Role save(Role role);

}
