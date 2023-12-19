package com.gestioncabinet.dao;

import com.gestioncabinet.entities.Soin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SoinRepository extends CrudRepository<Soin,Long> {
    Soin findByNom(String nom);
    Soin findSoinById(Long id);
}