package com.ascending.service;

import com.ascending.model.Role;
import com.ascending.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;
    public Role getRoleById(Long id){ return roleDao.getRoleById(id);}
    public Role save(Role role){return roleDao.save(role);}
}
