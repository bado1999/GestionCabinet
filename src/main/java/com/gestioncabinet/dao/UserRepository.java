package com.gestioncabinet.dao;

import com.gestioncabinet.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
