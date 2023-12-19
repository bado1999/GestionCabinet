package com.gestioncabinet.dao;

import com.gestioncabinet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
}
