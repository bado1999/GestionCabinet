package com.gestioncabinet.dao;

import com.gestioncabinet.entities.Soin;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SoinRepository extends MongoRepository<Soin,String> {
    Soin findByNom(String nom);
    Soin findSoinById(String id);
}