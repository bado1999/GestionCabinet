package com.gestioncabinet.dao;

import com.gestioncabinet.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,String>{
    Role findByRoleName(String roleName);
}
